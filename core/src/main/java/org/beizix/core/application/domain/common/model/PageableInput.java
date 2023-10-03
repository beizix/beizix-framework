package org.beizix.core.application.domain.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.beizix.core.config.enums.OrderDir;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class PageableInput {
  private Integer pageNumber;
  private Integer pageSize;
  private String orderBy;
  private OrderDir orderDir;
}
