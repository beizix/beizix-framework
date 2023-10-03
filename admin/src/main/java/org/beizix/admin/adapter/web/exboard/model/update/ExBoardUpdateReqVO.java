package org.beizix.admin.adapter.web.exboard.model.update;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ExBoardUpdateReqVO extends ExBoardUpdateBase {

  public ExBoardUpdateReqVO() {}

  @Override
  @NotBlank(message = "{valid.common.required}")
  public String getTitle() {
    return super.getTitle();
  }

  @Override
  @NotNull(message = "{valid.common.required}")
  public LocalDateTime getBoardStartDate() {
    return super.getBoardStartDate();
  }

  @Override
  @NotNull(message = "{valid.common.required}")
  public LocalDateTime getBoardEndDate() {
    return super.getBoardEndDate();
  }

  @Override
  @NotBlank(message = "{valid.common.required}")
  public String getContent() {
    return super.getContent();
  }
}
