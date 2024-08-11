package org.beizix.core.usecase.user.removeRoleWithPrivilege.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class RemoveRoleWithPrivilegeCmd {
  private String roleId;
}
