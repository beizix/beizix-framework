package org.beizix.core.config.application.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.beizix.core.config.application.enums.OrderDir;

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
