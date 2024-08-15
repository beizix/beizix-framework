package app.module.core.usecase.user.createUser.ports.application.domain;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CreateUserCmd {
  private String id;
  private String password;
  private String email;
  private boolean accountDisabled;
  private Integer loginFailCnt;
  private Boolean accountLocked;
  private Set<String> roles;
}
