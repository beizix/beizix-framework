package org.beizix.security.application.domain.admin.model.save;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.*;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveInput;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminSaveInput {
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
  private Set<AdminWithRoleSaveInput> withRoles;
}
