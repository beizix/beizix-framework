package app.module.admin.usecase.user.updateUser.adapters.web.model;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class UpdateUserReqVO {
  @NotNull(message = "{valid.common.required}")
  private String id;

  @NotBlank(message = "{valid.common.required}")
  @Pattern(
      regexp =
          "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
      message = "{valid.common.email.wrong}")
  private String email;

  @NotNull(message = "{valid.common.required}")
  private Boolean accountDisabled;

  @NotNull(message = "{valid.common.required}")
  private Integer loginFailCnt;

  @NotNull(message = "{valid.common.required}")
  private Boolean accountLocked;

  @NotEmpty(message = "{valid.common.required}")
  private Set<String> roles;
}
