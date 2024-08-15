package app.module.admin.usecase.role.list.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PrivilegeElement {
  private String id;
  private String description;
}
