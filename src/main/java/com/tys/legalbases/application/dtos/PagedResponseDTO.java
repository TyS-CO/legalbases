package com.tys.legalbases.application.dtos;

import java.util.List;

public record PagedResponseDTO<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {}
