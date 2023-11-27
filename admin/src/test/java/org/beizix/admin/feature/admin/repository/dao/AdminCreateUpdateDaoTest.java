package org.beizix.admin.feature.admin.repository.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.admin.usecase.admin.save.application.port.in.AdminSavePortIn;
import org.beizix.admin.usecase.admin.view.application.port.in.AdminViewPortIn;
import org.beizix.admin.usecase.admin.save.application.port.out.AdminSavePortOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
@Slf4j
class AdminCreateUpdateDaoTest {
  static {
    System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
  }

  @Autowired AdminSavePortOut adminCreateUpdateDao;
  @Autowired AdminViewPortIn adminViewPortIn;
  @Autowired AdminSavePortIn adminSavePortIn;

  @Autowired AdminRepo adminRepo;

  @Test
  @DisplayName("operate - AdminCreateDao")
  void operate() {

    adminSavePortIn.connect(
        "admin_for_createDao_test",
        "test.1@#$",
        "admin_for_createDao_test@test.com",
        false,
        false,
        Arrays.asList("ROLE_SUPER", "ROLE_STAFF"));

    adminViewPortIn
        .connect("admin_for_createDao_test")
        .ifPresent(
            retrieveUser ->
                assertEquals(
                    Arrays.asList("ROLE_SUPER", "ROLE_STAFF").size(),
                    retrieveUser.getRoles().size()));

    adminSavePortIn.connect(
        "admin_for_createDao_test",
        "test.1@#$",
        "admin_for_createDao_test@test.com",
        false,
        false,
        List.of("ROLE_STAFF"));
    assertEquals(
        1,
        adminViewPortIn.connect("admin_for_createDao_test").orElseThrow().getRoles().size(),
        "기존 권한들을 (내부적으로) 삭제하고 새로이 추가 등록되기에 withRoles 의 size 는 1이다.");
  }
}
