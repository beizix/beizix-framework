package org.beizix.core.feature.loggedInUser.persistence.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUser;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUserId;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
class LoggedInUserCreateUpdateDaoTest {
  @Autowired LoggedInUserCreateUpdateDao loggedInUserCreateUpdateDao;

  @Test
  @DisplayName("operate - 현재 로그인 사용자 저장")
  void operate() {
    LoggedInUser createdItem =
        loggedInUserCreateUpdateDao.operate(
            LoggedInUser.builder()
                .loggedInUserId(
                    LoggedInUserId.builder().appType(AppType.ADMIN).id("TEST_USER").build())
                .lastLoggedInAt(LocalDateTime.now())
                .build());

    assertAll(
        "operate - LoggedInUser created",
        () -> assertNotNull(createdItem, "LoggedInUser is created."),
        () -> assertEquals(AppType.ADMIN, createdItem.getLoggedInUserId().getAppType()));
  }
}
