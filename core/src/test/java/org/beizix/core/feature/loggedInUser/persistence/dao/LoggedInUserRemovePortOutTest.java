package org.beizix.core.feature.loggedInUser.persistence.dao;

import org.beizix.core.application.port.out.loggedinuser.LoggedInUserSavePortOut;
import org.beizix.core.application.port.out.loggedinuser.LoggedInUserRemovePortOut;
import org.beizix.core.application.port.out.loggedinuser.LoggedInUserViewPortOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
class LoggedInUserRemovePortOutTest {
  @Autowired
  LoggedInUserViewPortOut loggedInUserViewPortOut;
  @Autowired
  LoggedInUserSavePortOut loggedInUserSavePortOut;
  @Autowired
  LoggedInUserRemovePortOut loggedInUserRemovePortOut;

  @BeforeEach
  public void beforeEach() {
    loggedInUserSavePortOut.connect(
        LoggedInUserInput.builder()
            .loggedInUserId(LoggedInUserIdInput.builder().appType(AppType.ADMIN).id("TEST_USER").build())
            .lastLoggedInAt(LocalDateTime.now())
            .build());
  }

  @Test
  @DisplayName("operate - 현재 로그인 사용자 삭제")
  void operate() {
    loggedInUserRemovePortOut.connect(
        LoggedInUserIdInput.builder().appType(AppType.ADMIN).id("TEST_USER").build());

    assertNull(
        loggedInUserViewPortOut.connect(
            LoggedInUserIdInput.builder().appType(AppType.ADMIN).id("TEST_USER").build()));
  }
}
