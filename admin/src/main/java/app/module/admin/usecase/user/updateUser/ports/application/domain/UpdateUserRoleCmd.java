package app.module.admin.usecase.user.updateUser.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserRoleCmd {
  private String id;
}
