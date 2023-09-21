package org.beizix.admin.config.security;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.beizix.security.application.domain.admin.model.save.AdminSaveReq;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveReq;
import org.beizix.security.application.domain.role.model.save.RoleSaveMinimumReq;
import org.beizix.security.application.port.in.admin.AdminSaveService;
import org.beizix.security.application.port.in.admin.AdminViewService;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AdminAuthFailHandlerTest {
  static {
    System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
  }

  @Autowired MockMvc mockMvc;
  @Autowired AdminViewService adminViewService;
  @Autowired AdminSaveService adminSaveService;

  @Value("${org.beizix.admin.auth.fail.permit}")
  Integer loginFailPermit;

  String testUsername = "testUser";

  @BeforeAll
  public void beforeAll() {
    adminSaveService.operate(
        AdminSaveReq.builder()
            .id(testUsername)
            .password("test.1@#$")
            .email("xx4@test.com")
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
  @DisplayName("operate - 계정 잠김")
  void onAuthenticationFailure() throws Exception {
    String badPassword = "bad-password.1@#$";
    String goodPassword = "test.1@#$";

    for (int i = 0; i < loginFailPermit; i++) {
      mockMvc.perform(formLogin().user(testUsername).password(badPassword));
    }

    assertTrue(
        adminViewService.operate(testUsername).orElseThrow().getAccountLocked(),
        String.format("%s 회 이상 로그인 실패로 계정이 잠김처리되어야 한다.", loginFailPermit));

    mockMvc
        .perform(formLogin().user(testUsername).password(goodPassword))
        .andExpect(unauthenticated());
  }
}
