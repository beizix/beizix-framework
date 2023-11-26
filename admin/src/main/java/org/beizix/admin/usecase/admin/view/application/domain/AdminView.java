package org.beizix.admin.usecase.admin.view.application.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.security.adapter.persistence.admin.model.Admin;
import org.beizix.security.application.domain.admin.model.view.RoleOutput;

/** DTO for {@link Admin} */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminView implements Serializable {
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
  private List<RoleOutput> roles;
}