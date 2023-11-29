package org.beizix.core.config.adapter.web.rest.error;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RestErrorVO {
  private List<RestObjectErrorVO> objectErrors;
  private List<RestFieldErrorVO> fieldErrors;
}
