package org.beizix.admin.config.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.beizix.security.application.domain.admin.model.save.AdminSaveReq;
import org.beizix.security.application.domain.admin_role.model.save.AdminWithRoleSaveReq;
import org.beizix.security.application.domain.role.model.save.RoleSaveMinimumReq;
import org.beizix.security.application.port.in.admin.AdminSaveService;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AdminDetailsServiceTest {

  static {
    System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
  }

  @Autowired MockMvc mockMvc;
  @Autowired AdminSaveService adminSaveService;

  String username = "test";
  String password = "test.1@#$";
  String oldUsername = "admin_for_old_password_test";

  @BeforeAll
  public void beforeAll() {
    adminSaveService.operate(
        AdminSaveReq.builder()
            .id(username)
            .password(password)
            .email("xx1@test.com")
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

    adminSaveService.operate(
        AdminSaveReq.builder()
            .id(oldUsername)
            .password(password)
            .email("xx2@test.com")
            .passwordUpdatedAt(LocalDateTime.now().minusDays(120)) // 패스워드 변경일 지남
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
  @DisplayName("operate - 로그인 성공")
  void operate1() throws Exception {
    mockMvc.perform(formLogin().user(username).password(password)).andExpect(authenticated());
  }

  @Test
  @DisplayName("operate - 로그인 실패")
  void operate2() throws Exception {
    String badPassword = "bad-password.1@#$";
    mockMvc.perform(formLogin().user(username).password(badPassword)).andExpect(unauthenticated());
  }

  //  @Test
  //  @DisplayName("operate - 패스워드 유효기간 지남")
  //  void operate3() throws Exception {
  //
  // mockMvc.perform(formLogin().user(oldUsername).password(password)).andExpect(unauthenticated());
  //  }
}
