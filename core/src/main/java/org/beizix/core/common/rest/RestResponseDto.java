package org.beizix.core.common.rest;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RestResponseDto<T> {
    private String message;

    private T item;
    private List<T> items;
}
