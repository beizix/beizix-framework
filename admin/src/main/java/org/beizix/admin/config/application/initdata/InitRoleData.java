package org.beizix.admin.config.application.initdata;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.config.adapter.persistence.entity.Privilege;
import org.beizix.admin.config.adapter.persistence.entity.Role;
import org.beizix.admin.config.adapter.persistence.entity.RoleWithPrivilege;
import org.beizix.admin.usecase.privilege.view.adapters.persistence.PrivilegeViewRepo;
import org.beizix.admin.usecase.role.save.adapter.persistence.RoleSaveRepo;
import org.beizix.core.config.application.util.PropertyUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class InitRoleData implements ApplicationRunner {
  private final RoleSaveRepo roleRepo;
  private final PrivilegeViewRepo privilegeViewRepo;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    // 권한 `privilege` 정보 가져오기
    Privilege read = privilegeViewRepo.findById("READ").orElseThrow();
    Privilege write = privilegeViewRepo.findById("WRITE").orElseThrow();
    Privilege delete = privilegeViewRepo.findById("DELETE").orElseThrow();
    Privilege update = privilegeViewRepo.findById("UPDATE").orElseThrow();
    Privilege download = privilegeViewRepo.findById("DOWNLOAD").orElseThrow();

    int orderNo = -1;

    roleRepo.save(
        new Role(
            "ROLE_SUPER",
            "SUPER 관리자",
            orderNo + 1,
            Set.of(
                new RoleWithPrivilege(null, new Role("ROLE_SUPER"), read),
                new RoleWithPrivilege(null, new Role("ROLE_SUPER"), write),
                new RoleWithPrivilege(null, new Role("ROLE_SUPER"), delete),
                new RoleWithPrivilege(null, new Role("ROLE_SUPER"), update),
                new RoleWithPrivilege(null, new Role("ROLE_SUPER"), download))));

    roleRepo.save(
        new Role(
            "ROLE_MANAGER",
            "매니저",
            orderNo + 1,
            Set.of(
                new RoleWithPrivilege(null, new Role("ROLE_MANAGER"), read),
                new RoleWithPrivilege(null, new Role("ROLE_MANAGER"), write),
                new RoleWithPrivilege(null, new Role("ROLE_MANAGER"), download))));

    roleRepo.save(
        new Role(
            "ROLE_STAFF",
            "스텝",
            orderNo + 1,
            Set.of(
                new RoleWithPrivilege(null, new Role("ROLE_MANAGER"), read),
                new RoleWithPrivilege(null, new Role("ROLE_MANAGER"), download))));
  }
}
