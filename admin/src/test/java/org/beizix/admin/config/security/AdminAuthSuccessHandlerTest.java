package org.beizix.admin.config.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUserId;
import org.beizix.core.feature.loggedInUser.persistence.dao.LoggedInUserViewDao;
import org.beizix.security.application.domain.admin.model.save.AdminSaveReq;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveReq;
import org.beizix.security.application.domain.role.model.save.RoleSaveMinimumReq;
import org.beizix.security.application.port.in.admin.AdminSaveService;
import org.beizix.security.application.port.in.admin.AdminViewService;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AdminAuthSuccessHandlerTest {
  static {
    System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
  }

  @Autowired MockMvc mockMvc;
  @Autowired AdminViewService adminViewService;
  @Autowired LoggedInUserViewDao loggedInUserViewDao;
  @Autowired AdminSaveService adminSaveService;

  @Value("${org.beizix.session.maximum.num}")
  private Integer maxSessionNum;

  String username = "admin_for_initFailCnt_aspect_test";
  String password = "test.1@#$";

  @BeforeAll
  public void beforeAll() {
    adminSaveService.operate(
        AdminSaveReq.builder()
            .id(username)
            .password(password)
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
            .build());
  }

  @Test
  void onAuthenticationSuccess() throws Exception {

    String badPassword = "bad-password.1@#$";

    mockMvc.perform(formLogin().user(username).password(badPassword)).andExpect(unauthenticated());

    assertEquals(
        1,
        adminViewService.operate(username).orElseThrow().getLoginFailCnt(),
        "로그인 실패시 실패회수가 1증가 해야한다.");

    mockMvc.perform(formLogin().user(username).password(password)).andExpect(authenticated());

    assertEquals(
        0,
        adminViewService.operate(username).orElseThrow().getLoginFailCnt(),
        "로그인 성공시 실패회수가 0으로 초기화 되야한다.");

    if (maxSessionNum == 1) {
      assertNotNull(
          loggedInUserViewDao.operate(
              LoggedInUserId.builder().appType(AppType.ADMIN).id(username).build()),
          "maxSessionNum 이 1일때, 로그인 기록 레코드가 생성되어야 한다.");
    }
  }
}
