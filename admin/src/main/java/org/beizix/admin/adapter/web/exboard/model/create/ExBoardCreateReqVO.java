package org.beizix.admin.adapter.web.exboard.model.create;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ExBoardCreateReqVO extends ExBoardCreateBase {

  public ExBoardCreateReqVO() {}

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
