package com.ms.email.consumer;

import com.ms.email.model.dto.EmailConsumerDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailConsumerDto email) {
        System.out.println(email.toString());
    }
}
