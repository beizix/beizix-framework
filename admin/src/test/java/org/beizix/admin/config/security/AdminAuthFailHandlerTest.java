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
import org.beizix.security.application.domain.admin.model.save.AdminSaveInput;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveInput;
import org.beizix.security.application.domain.role.model.save.RoleSaveReferInput;
import org.beizix.security.application.port.in.admin.AdminSavePortIn;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AdminAuthFailHandlerTest {
  static {
    System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
  }

  @Autowired MockMvc mockMvc;
  @Autowired
  AdminViewPortIn adminViewPortIn;
  @Autowired
  AdminSavePortIn adminSavePortIn;

  @Value("${org.beizix.admin.auth.fail.permit}")
  Integer loginFailPermit;

  String testUsername = "testUser";

  @BeforeAll
  public void beforeAll() {
    adminSavePortIn.connect(
        AdminSaveInput.builder()
            .id(testUsername)
            .password("test.1@#$")
            .email("xx4@test.com")
            .passwordUpdatedAt(LocalDateTime.now())
            .withRoles(
                Set.of(
                    AdminWithRoleSaveInput.builder()
                        .role(new RoleSaveReferInput("ROLE_SUPER"))
                        .build(),
                    AdminWithRoleSaveInput.builder()
                        .role(new RoleSaveReferInput("ROLE_STAFF"))
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
        adminViewPortIn.connect(testUsername).orElseThrow().getAccountLocked(),
        String.format("%s 회 이상 로그인 실패로 계정이 잠김처리되어야 한다.", loginFailPermit));

    mockMvc
        .perform(formLogin().user(testUsername).password(goodPassword))
        .andExpect(unauthenticated());
  }
}
