package app.module.core.usecase.user.removeUserWithRole.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class RemoveUserWithRoleCmd {
  private String userId;
}
