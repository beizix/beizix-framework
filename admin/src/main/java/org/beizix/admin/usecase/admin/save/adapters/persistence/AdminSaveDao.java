package org.beizix.admin.usecase.admin.save.adapters.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.admin.usecase.admin.save.ports.AdminSavePortOut;
import org.beizix.admin.config.adapter.persistence.entity.Admin;
import org.beizix.admin.config.adapter.persistence.entity.AdminWithRole;
import org.beizix.admin.config.adapter.persistence.entity.Role;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AdminSaveDao implements AdminSavePortOut {
  private final AdminSaveRepo saveRepo;
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
        saveRepo.save(
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
