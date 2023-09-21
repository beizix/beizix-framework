package org.beizix.core.feature.loggedInUser.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUser;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUserId;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
class LoggedInUserViewDaoTest {
  @Autowired LoggedInUserViewDao loggedInUserViewDao;
  @Autowired LoggedInUserCreateUpdateDao loggedInUserCreateUpdateDao;

  @BeforeEach
  public void beforeEach() {
    loggedInUserCreateUpdateDao.operate(
        LoggedInUser.builder()
            .loggedInUserId(LoggedInUserId.builder().appType(AppType.ADMIN).id("TEST_USER").build())
            .lastLoggedInAt(LocalDateTime.now())
            .build());
  }

  @Test
  @DisplayName("operate - 현재 로그인 사용자 조회")
  void operate() {
    LoggedInUser loggedInUser =
        loggedInUserViewDao.operate(
            LoggedInUserId.builder().appType(AppType.ADMIN).id("TEST_USER").build());
    
    assertNotNull(loggedInUser, "로그인 사용자 조회 성공");
  }
}
