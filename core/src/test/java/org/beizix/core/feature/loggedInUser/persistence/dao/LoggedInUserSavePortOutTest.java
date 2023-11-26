package org.beizix.core.feature.loggedInUser.persistence.dao;

import org.beizix.core.application.port.out.loggedinuser.LoggedInUserSavePortOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
class LoggedInUserSavePortOutTest {
  @Autowired
  LoggedInUserSavePortOut loggedInUserSavePortOut;

  @Test
  @DisplayName("operate - 현재 로그인 사용자 저장")
  void operate() {
    LoggedInUserInput createdItem =
        loggedInUserSavePortOut.connect(
            LoggedInUserInput.builder()
                .loggedInUserId(
                    LoggedInUserIdInput.builder().appType(AppType.ADMIN).id("TEST_USER").build())
                .lastLoggedInAt(LocalDateTime.now())
                .build());

    assertAll(
        "operate - LoggedInUser created",
        () -> assertNotNull(createdItem, "LoggedInUser is created."),
        () -> assertEquals(AppType.ADMIN, createdItem.getLoggedInUserId().getAppType()));
  }
}
