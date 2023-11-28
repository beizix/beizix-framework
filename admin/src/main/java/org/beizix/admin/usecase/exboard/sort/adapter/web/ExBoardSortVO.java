package org.beizix.admin.usecase.exboard.sort.adapter.web;

import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ExBoardSortVO {
  @NotNull(message = "{valid.common.required}")
  private Long id;
  @NotNull(message = "{valid.common.required}")
  private Integer orderNo;
}
