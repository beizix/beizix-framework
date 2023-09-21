package org.beizix.security.application.domain.admin.model.view;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.security.adapter.persistence.role_privilege.model.RoleWithPrivilege;

/** DTO for {@link RoleWithPrivilege} */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithPrivilegeResp implements Serializable {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private Integer id;
  private PrivilegeResp privilege;
}
