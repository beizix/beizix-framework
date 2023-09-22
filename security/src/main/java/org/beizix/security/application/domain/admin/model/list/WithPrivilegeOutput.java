package org.beizix.security.application.domain.admin.model.list;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.security.adapter.persistence.role_privilege.model.RoleWithPrivilege;

/**
 * DTO for {@link
 * RoleWithPrivilege}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithPrivilegeOutput implements Serializable {
  private Integer id;
  private PrivilegeOutput privilege;
}
