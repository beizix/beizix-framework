package org.beizix.admin.adapter.web.exboard.model.sort;

import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ExBoardSortReqVO {
  @NotNull(message = "{valid.common.required}")
  private Long id;
  @NotNull(message = "{valid.common.required}")
  private Integer orderNo;
}
