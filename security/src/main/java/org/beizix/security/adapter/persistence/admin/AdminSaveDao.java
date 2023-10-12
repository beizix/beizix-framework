package org.beizix.security.adapter.persistence.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.security.adapter.persistence.admin.model.Admin;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.adapter.persistence.admin_role.model.AdminWithRole;
import org.beizix.security.adapter.persistence.admin_role.repository.AdminWithRoleRepo;
import org.beizix.security.adapter.persistence.role.model.Role;
import org.beizix.security.application.port.out.admin.AdminSavePortOut;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AdminSaveDao implements AdminSavePortOut {
  private final AdminRepo adminRepo;
  private final AdminWithRoleRepo adminWithRoleRepo;

  @Override
  @Transactional
  public String connect(
      String id,
      String encodedPassword,
      LocalDateTime passwordUpdatedAt,
      String email,
      Boolean accountDisabled,
      Integer loginFailCnt,
      Boolean accountLocked,
      List<String> roleIds) {

    Admin createdItem =
        adminRepo.save(
            new Admin(
                id,
                encodedPassword,
                email,
                passwordUpdatedAt,
                accountDisabled,
                loginFailCnt,
                accountLocked,
                null));

    adminWithRoleRepo.deleteAllByAdminId(id);

    adminWithRoleRepo.saveAll(
        CollectionUtils.emptyIfNull(roleIds).stream()
            .map(roleId -> new AdminWithRole(null, new Admin(id), new Role(roleId)))
            .collect(Collectors.toList()));

    return createdItem.getId();
  }
}
