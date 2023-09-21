package org.beizix.core.common.rest;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestObjectErrorDto {
  private String message;
}
