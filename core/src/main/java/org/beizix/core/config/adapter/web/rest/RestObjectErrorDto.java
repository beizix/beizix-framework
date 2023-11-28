package org.beizix.core.config.adapter.web.rest;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestObjectErrorDto {
  private String message;
}
