package app.module.admin.usecase.user.getUsers.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetUsersPrivilege {
  private String id;
  private String description;
}
