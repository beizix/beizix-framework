package org.beizix.security.application.domain.admin.model.view;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.security.adapter.persistence.role.model.Role;

/** DTO for {@link Role} */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleOutput implements Serializable {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private String id;
  private String description;
  private Integer orderNo;
  private Set<WithPrivilegeOutput> withPrivileges;
}
