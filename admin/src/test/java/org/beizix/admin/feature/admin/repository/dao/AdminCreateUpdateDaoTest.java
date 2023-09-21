package org.beizix.admin.feature.admin.repository.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.application.domain.admin.model.save.AdminSaveReq;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveReq;
import org.beizix.security.application.domain.role.model.save.RoleSaveMinimumReq;
import org.beizix.security.application.port.in.admin.AdminSaveService;
import org.beizix.security.application.port.in.admin.AdminViewService;
import org.beizix.security.application.port.in.admin_role.AdminWithRoleRemoveService;
import org.beizix.security.application.port.out.admin.AdminSaveDao;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
@Slf4j
class AdminCreateUpdateDaoTest {
  static {
    System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
  }

  @Autowired AdminSaveDao adminCreateUpdateDao;
  @Autowired AdminViewService adminViewService;
  @Autowired AdminSaveService adminSaveService;
  @Autowired AdminWithRoleRemoveService adminWithRoleRemoveService;

  @Autowired AdminRepo adminRepo;

  @Test
  @DisplayName("operate - AdminCreateDao")
  void operate() {

    AdminSaveReq adminDto =
        AdminSaveReq.builder()
            .id("admin_for_createDao_test")
            .password("test.1@#$")
            .email("admin_for_createDao_test@test.com")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveReq.builder()
                        .role(new RoleSaveMinimumReq("ROLE_SUPER"))
                        .build(),
                    AdminWithRoleSaveReq.builder()
                        .role(new RoleSaveMinimumReq("ROLE_STAFF"))
                        .build()))
            .build();

    adminSaveService.operate(adminDto);

    adminViewService
        .operate("admin_for_createDao_test")
        .ifPresent(
            retrieveUser ->
                assertEquals(adminDto.getWithRoles().size(), retrieveUser.getWithRoles().size()));

    adminSaveService.operate(
        AdminSaveReq.builder()
            .id("admin_for_createDao_test")
            .password("test.1@#$")
            .email("admin_for_createDao_test@test.com")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveReq.builder()
                        .role(new RoleSaveMinimumReq("ROLE_STAFF"))
                        .build()))
            .build());

    assertEquals(
        1,
        adminViewService.operate("admin_for_createDao_test").orElseThrow().getWithRoles().size(),
        "기존 권한들을 (내부적으로) 삭제하고 새로이 추가 등록되기에 withRoles 의 size 는 1이다.");
  }

  @Test
  @DisplayName("operate - expect exception")
  void operate2() {
    assertThrows(
        RuntimeException.class,
        () ->
            adminCreateUpdateDao.operate(
                AdminSaveReq.builder()
                    .id("admin_for_createDao_test")
                    .password("test.1@#$")
                    .email("admin_for_createDao_test@test.com")
                    .passwordUpdatedAt(LocalDateTime.now())
                    .withRoles(
                        Set.of(
                            AdminWithRoleSaveReq.builder()
                                .role(new RoleSaveMinimumReq("NOT_EXIST_ROLE_1"))
                                .build(),
                            AdminWithRoleSaveReq.builder()
                                .role(new RoleSaveMinimumReq("NOT_EXIST_ROLE_2"))
                                .build(),
                            AdminWithRoleSaveReq.builder()
                                .role(new RoleSaveMinimumReq("NOT_EXIST_ROLE_3"))
                                .build()))
                    .build()),
        "존재하지 않는 Role 을 지정하면 예외가 발생한다.");
  }
}
