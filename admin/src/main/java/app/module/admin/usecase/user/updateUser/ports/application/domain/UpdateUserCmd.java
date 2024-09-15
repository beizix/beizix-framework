package app.module.admin.usecase.user.updateUser.ports.application.domain;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class UpdateUserCmd {
  private String id;
  private String email;
  private Boolean accountDisabled;
  private Integer loginFailCnt;
  private Boolean accountLocked;
  private Set<String> roles;
}
