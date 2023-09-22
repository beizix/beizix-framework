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
import org.beizix.security.application.domain.admin.model.save.AdminSaveInput;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveInput;
import org.beizix.security.application.domain.role.model.save.RoleSaveReferInput;
import org.beizix.security.application.port.in.admin.AdminSavePortIn;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;
import org.beizix.security.application.port.in.admin_role.AdminWithRoleRemovePortIn;
import org.beizix.security.application.port.out.admin.AdminSavePortOut;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
@Slf4j
class AdminCreateUpdateDaoTest {
  static {
    System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
  }

  @Autowired
  AdminSavePortOut adminCreateUpdateDao;
  @Autowired
  AdminViewPortIn adminViewPortIn;
  @Autowired
  AdminSavePortIn adminSavePortIn;
  @Autowired
  AdminWithRoleRemovePortIn adminWithRoleRemovePortIn;

  @Autowired AdminRepo adminRepo;

  @Test
  @DisplayName("operate - AdminCreateDao")
  void operate() {

    AdminSaveInput adminDto =
        AdminSaveInput.builder()
            .id("admin_for_createDao_test")
            .password("test.1@#$")
            .email("admin_for_createDao_test@test.com")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveInput.builder()
                        .role(new RoleSaveReferInput("ROLE_SUPER"))
                        .build(),
                    AdminWithRoleSaveInput.builder()
                        .role(new RoleSaveReferInput("ROLE_STAFF"))
                        .build()))
            .build();

    adminSavePortIn.connect(adminDto);

    adminViewPortIn
        .connect("admin_for_createDao_test")
        .ifPresent(
            retrieveUser ->
                assertEquals(adminDto.getWithRoles().size(), retrieveUser.getWithRoles().size()));

    adminSavePortIn.connect(
        AdminSaveInput.builder()
            .id("admin_for_createDao_test")
            .password("test.1@#$")
            .email("admin_for_createDao_test@test.com")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveInput.builder()
                        .role(new RoleSaveReferInput("ROLE_STAFF"))
                        .build()))
            .build());

    assertEquals(
        1,
        adminViewPortIn.connect("admin_for_createDao_test").orElseThrow().getWithRoles().size(),
        "기존 권한들을 (내부적으로) 삭제하고 새로이 추가 등록되기에 withRoles 의 size 는 1이다.");
  }

  @Test
  @DisplayName("operate - expect exception")
  void operate2() {
    assertThrows(
        RuntimeException.class,
        () ->
            adminCreateUpdateDao.connect(
                AdminSaveInput.builder()
                    .id("admin_for_createDao_test")
                    .password("test.1@#$")
                    .email("admin_for_createDao_test@test.com")
                    .passwordUpdatedAt(LocalDateTime.now())
                    .withRoles(
                        Set.of(
                            AdminWithRoleSaveInput.builder()
                                .role(new RoleSaveReferInput("NOT_EXIST_ROLE_1"))
                                .build(),
                            AdminWithRoleSaveInput.builder()
                                .role(new RoleSaveReferInput("NOT_EXIST_ROLE_2"))
                                .build(),
                            AdminWithRoleSaveInput.builder()
                                .role(new RoleSaveReferInput("NOT_EXIST_ROLE_3"))
                                .build()))
                    .build()),
        "존재하지 않는 Role 을 지정하면 예외가 발생한다.");
  }
}
