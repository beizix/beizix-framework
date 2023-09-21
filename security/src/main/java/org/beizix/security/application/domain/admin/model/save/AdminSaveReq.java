package org.beizix.security.application.domain.admin.model.save;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.*;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveReq;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminSaveReq {
  private String id;
  private String password;
  private String email;
  private String updatePassword;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime passwordUpdatedAt;

  private Boolean accountDisabled;
  private Integer loginFailCnt;
  private Boolean accountLocked;
  private Set<AdminWithRoleSaveReq> withRoles;
}
