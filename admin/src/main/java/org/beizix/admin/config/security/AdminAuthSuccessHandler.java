package org.beizix.admin.config.security;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.beizix.admin.config.aop.LoginSuccessOperateLog;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUser;
import org.beizix.core.feature.loggedInUser.application.model.LoggedInUserId;
import org.beizix.core.feature.loggedInUser.persistence.dao.LoggedInUserCreateUpdateDao;
import org.beizix.core.feature.loggedInUser.persistence.dao.LoggedInUserViewDao;
import org.beizix.security.application.port.in.admin.AdminSaveService;
import org.beizix.security.application.port.in.admin.AdminViewService;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.MessageUtil;

@Component
@RequiredArgsConstructor
public class AdminAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private final MessageUtil messageUtil;
  private final AdminViewService adminViewService;
  private final AdminSaveService adminSaveService;
  private final LoggedInUserViewDao loggedInUserViewDao;
  private final LoggedInUserCreateUpdateDao loggedInUserCreateUpdateDao;
  private final CommonUtil commonUtil;

  @Value("${org.beizix.password.validity.period.days}")
  private long passwordValidPeriodDays;

  @Value("${org.beizix.password.change.notice.period.days}")
  private long passwordChangeNoticeDays;

  @Value("${org.beizix.session.validity.period.seconds}")
  private long sessionValidPeriodSeconds;

  @Value("${org.beizix.session.maximum.num}")
  private Integer maxSessionNum;

  @Override
  @LoginSuccessOperateLog
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    AdminUserDetail adminUserDetail = (AdminUserDetail) authentication.getPrincipal();

    // 로그인 실패 회수 초기화
    adminViewService
        .operate(authentication.getName())
        .ifPresent(
            admin -> {
              adminSaveService.updateLoginFailCnt(admin.getId(), 0);
            });

    // 패스워드 변경일 공지
    if (passwordValidPeriodDays != -1
        && adminUserDetail.getRemainPasswordDays() <= passwordChangeNoticeDays) {
      commonUtil.addFlashAlertMessages(
          request.getSession(),
          messageUtil.getMessage(
              "org.beizix.password.change.notify", adminUserDetail.getRemainPasswordDays()));
    }

    if (maxSessionNum == 1) {
      // 동시 접속 (중복 로그인) 여부 판단 후 사용자에게 메세지 보이기
      if (isAlreadyLoggedInSomewhere(authentication.getName(), commonUtil.getClientIP(request))) {
        commonUtil.addFlashAlertMessages(
            request.getSession(), messageUtil.getMessage("org.beizix.auth.duplicate.login"));
      }

      // 로그인 활성화 기록 생성
      loggedInUserCreateUpdateDao.operate(
          LoggedInUser.builder()
              .loggedInUserId(
                  LoggedInUserId.builder()
                      .appType(AppType.ADMIN)
                      .id(authentication.getName())
                      .build())
              .clientIP(commonUtil.getClientIP(request))
              .lastLoggedInAt(LocalDateTime.now())
              .build());
    }

    super.onAuthenticationSuccess(request, response, authentication);
  }

  /** 사용자 로그인 여부 확인 */
  private boolean isAlreadyLoggedInSomewhere(String username, String currentIP) {
    LoggedInUser loggedInUser =
        loggedInUserViewDao.operate(
            LoggedInUserId.builder().appType(AppType.ADMIN).id(username).build());
    if (loggedInUser == null) return false;

    LocalDateTime lastLoggedInAt = loggedInUser.getLastLoggedInAt();

    return lastLoggedInAt.plusSeconds(sessionValidPeriodSeconds).isAfter(LocalDateTime.now())
        && !currentIP.equals(loggedInUser.getClientIP());
  }
}
