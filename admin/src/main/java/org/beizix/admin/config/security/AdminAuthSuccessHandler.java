package org.beizix.admin.config.security;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.config.aop.LoginSuccessOperateLog;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserIdInput;
import org.beizix.core.application.domain.loggedinuser.model.LoggedInUserInput;
import org.beizix.core.application.port.in.loggedinuser.LoggedInUserSavePortIn;
import org.beizix.core.application.port.in.loggedinuser.LoggedInUserViewPortIn;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.admin.usecase.admin.status.application.port.in.AdminUpdateLoginFailPortIn;
import org.beizix.admin.usecase.admin.view.application.port.in.AdminViewPortIn;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.MessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private final MessageUtil messageUtil;
  private final AdminViewPortIn adminViewPortIn;
  private final AdminUpdateLoginFailPortIn updateLoginFailPortIn;
  private final LoggedInUserViewPortIn loggedInUserViewPortIn;
  private final LoggedInUserSavePortIn loggedInUserSavePortIn;
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
    adminViewPortIn
        .connect(authentication.getName())
        .ifPresent(admin -> updateLoginFailPortIn.connect(admin.getId(), 0));

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
      loggedInUserSavePortIn.connect(
          LoggedInUserInput.builder()
              .loggedInUserId(
                  LoggedInUserIdInput.builder()
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
    LoggedInUserInput loggedInUser =
        loggedInUserViewPortIn.connect(
            LoggedInUserIdInput.builder().appType(AppType.ADMIN).id(username).build());
    if (loggedInUser == null) return false;

    LocalDateTime lastLoggedInAt = loggedInUser.getLastLoggedInAt();

    return lastLoggedInAt.plusSeconds(sessionValidPeriodSeconds).isAfter(LocalDateTime.now())
        && !currentIP.equals(loggedInUser.getClientIP());
  }
}
