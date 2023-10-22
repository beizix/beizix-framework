package org.beizix.admin.adapter.web.admin.model.update;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.application.domain.common.model.AuditOutput;

@Getter
@Setter
@AllArgsConstructor
public class AdminUpdateBindingVO implements AuditOutput {
  private final String createdBy;

  private final LocalDateTime createdAt;

  private final String updatedBy;

  private final LocalDateTime updatedAt;

  private String id;
  private String updatePassword;

  @NotBlank(message = "{valid.common.required}")
  @Pattern(
      regexp =
          "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
      message = "{valid.common.email.wrong}")
  private String email;

  private LocalDateTime passwordUpdatedAt;
  private Boolean accountDisabled;
  private Integer loginFailCnt;
  private Boolean accountLocked;
  private List<String> roleIds;
}
