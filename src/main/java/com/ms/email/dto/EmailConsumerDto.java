package com.ms.email.dto;

import java.util.UUID;

public record EmailConsumerDto(UUID id, String email, String subject, String text) {
}
