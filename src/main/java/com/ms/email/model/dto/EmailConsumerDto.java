package com.ms.email.model.dto;

import java.util.UUID;

public record EmailConsumerDto(UUID userId, String email, String subject, String text) {
}
