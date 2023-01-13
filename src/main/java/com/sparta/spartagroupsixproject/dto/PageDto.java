package com.sparta.spartagroupsixproject.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageDto {

    int page;

    int size;

    String sortBy;

    boolean isAsc;

}
