package org.beizix.core.configuration.adapter.web.rest;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RestErrorDto {
  private List<RestObjectErrorDto> objectErrors;
  private List<RestFieldErrorDto> fieldErrors;
}
