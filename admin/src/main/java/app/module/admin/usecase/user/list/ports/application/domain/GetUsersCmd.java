package app.module.admin.usecase.user.list.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetUsersCmd {
  private final String searchField;
  private final String searchValue;
  private final String searchRole;
}
