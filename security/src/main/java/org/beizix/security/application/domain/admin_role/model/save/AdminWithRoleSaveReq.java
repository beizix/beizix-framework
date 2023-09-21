package org.beizix.security.application.domain.admin_role.model.save;

import lombok.*;
import org.beizix.security.application.domain.admin.model.save.AdminSaveMinimumReq;
import org.beizix.security.application.domain.role.model.save.RoleSaveMinimumReq;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminWithRoleSaveReq {
  private Long id;
  private AdminSaveMinimumReq admin;
  private RoleSaveMinimumReq role;
}
