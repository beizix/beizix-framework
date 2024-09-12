package app.module.admin.usecase.user.getUsers.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetUsersRole {
  private String id;
  private String description;
  private Set<GetUsersPrivilege> privileges;
}
