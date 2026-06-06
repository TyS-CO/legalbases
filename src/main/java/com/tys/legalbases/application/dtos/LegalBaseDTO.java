package com.tys.legalbases.application.dtos;

import java.time.LocalDateTime;

public record LegalBaseDTO(
    Long id,
    String title,
    String description,
    String type,
    LocalDateTime publishedAt,
    String sourceUrl
) {}
