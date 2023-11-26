package org.beizix.core.feature.loggedInUser.persistence.dao;

import org.beizix.core.application.port.in.loggedinuser.LoggedInUserSavePortIn;
import org.beizix.core.application.port.out.loggedinuser.LoggedInUserViewPortOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
class LoggedInUserViewPortOutTest {
  @Autowired
  LoggedInUserViewPortOut loggedInUserViewPortOut;
  @Autowired
  LoggedInUserSavePortIn loggedInUserSavePortIn;

  @BeforeEach
  public void beforeEach() {
    loggedInUserSavePortIn.connect(
        LoggedInUserInput.builder()
            .loggedInUserId(LoggedInUserIdInput.builder().appType(AppType.ADMIN).id("TEST_USER").build())
            .lastLoggedInAt(LocalDateTime.now())
            .build());
  }

  @Test
  @DisplayName("operate - 현재 로그인 사용자 조회")
  void operate() {
    LoggedInUserInput loggedInUser =
        loggedInUserViewPortOut.connect(
            LoggedInUserIdInput.builder().appType(AppType.ADMIN).id("TEST_USER").build());
    
    assertNotNull(loggedInUser, "로그인 사용자 조회 성공");
  }
}
