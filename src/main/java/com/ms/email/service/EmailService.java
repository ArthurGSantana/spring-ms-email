package com.ms.email.service;

import com.ms.email.enums.StatusEmail;
import com.ms.email.model.dto.EmailConsumerDto;
import com.ms.email.model.entity.Email;
import com.ms.email.repository.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(EmailConsumerDto emailConsumerDto) {
            var email = new Email();
            BeanUtils.copyProperties(emailConsumerDto, email);

        try {

            var message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());

            javaMailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);

        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.save(email);
        }
    }
}
