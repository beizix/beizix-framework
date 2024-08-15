package app.module.admin.usecase.exboard.sort.adapters.web.model;

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
