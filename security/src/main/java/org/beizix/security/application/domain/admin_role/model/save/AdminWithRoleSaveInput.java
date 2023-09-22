package org.beizix.security.application.domain.admin_role.model.save;

import lombok.*;
import org.beizix.security.application.domain.admin.model.save.AdminSaveReferInput;
import org.beizix.security.application.domain.role.model.save.RoleSaveReferInput;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminWithRoleSaveInput {
  private Long id;
  private AdminSaveReferInput admin;
  private RoleSaveReferInput role;
}
