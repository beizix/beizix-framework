package org.beizix.security.application.domain.admin.model.view;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.security.adapter.persistence.admin_role.model.AdminWithRole;

/** DTO for {@link AdminWithRole} */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithRoleOutput implements Serializable {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private Long id;
  private RoleOutput role;
}
