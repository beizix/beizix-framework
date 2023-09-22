package org.beizix.security.application.domain.admin.model.list;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.security.adapter.persistence.admin.model.Admin;

/** DTO for {@link Admin} */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminListOutput implements Serializable {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private String id;
  private String password;
  private String email;
  private LocalDateTime passwordUpdatedAt;
  private Boolean accountDisabled;
  private Integer loginFailCnt;
  private Boolean accountLocked;
  private Set<WithRoleOutput> withRoles = new LinkedHashSet<>();
}
