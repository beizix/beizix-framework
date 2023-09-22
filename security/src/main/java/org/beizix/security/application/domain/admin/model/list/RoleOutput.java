package org.beizix.security.application.domain.admin.model.list;

import java.io.Serializable;
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
  private String id;
  private String description;
  private Integer orderNo;
  private Set<WithPrivilegeOutput> withPrivileges;
}
