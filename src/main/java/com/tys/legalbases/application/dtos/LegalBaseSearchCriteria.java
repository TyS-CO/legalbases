package com.tys.legalbases.application.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LegalBaseSearchCriteria {

    private final String search;

    @Builder.Default
    private final int page = 0;

    @Builder.Default
    private final int size = 10;

    @Builder.Default
    private final String sortBy = "publishedAt";

    @Builder.Default
    private final String sortDir = "desc";
}
