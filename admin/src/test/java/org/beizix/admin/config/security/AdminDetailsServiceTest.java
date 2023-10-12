package org.beizix.admin.config.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

import java.util.Arrays;
import org.beizix.security.application.port.in.admin.AdminSavePortIn;
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

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AdminDetailsServiceTest {

  static {
    System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
  }

  @Autowired MockMvc mockMvc;
  @Autowired AdminSavePortIn adminSavePortIn;

  String username = "test";
  String password = "test.1@#$";
  String oldUsername = "admin_for_old_password_test";

  @BeforeAll
  public void beforeAll() {
    adminSavePortIn.connect(
        username,
        password,
        "xx1@test.com",
        false,
        false,
        Arrays.asList("ROLE_SUPER", "ROLE_STAFF"));


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
