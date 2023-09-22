package org.beizix.security.application.domain.admin.model.list;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.security.adapter.persistence.admin_role.model.AdminWithRole;

/** DTO for {@link AdminWithRole} */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithRoleOutput implements Serializable {
  private Long id;
  private RoleOutput role;
}
