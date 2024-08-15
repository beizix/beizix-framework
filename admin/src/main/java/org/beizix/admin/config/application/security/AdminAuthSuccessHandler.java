package org.beizix.admin.config.application.security;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.config.application.aop.LoginSuccessOperateLog;
import org.beizix.admin.usecase.admin.status.ports.AdminUpdateLoginFailPortIn;
import org.beizix.admin.usecase.admin.view.ports.AdminViewPortIn;
import org.beizix.admin.usecase.loggedinuser.save.ports.LoggedInUserSavePortIn;
import org.beizix.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserIdSaveCmd;
import org.beizix.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserSaveCmd;
import org.beizix.admin.usecase.loggedinuser.view.ports.LoggedInUserViewPortIn;
import org.beizix.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserIdCmd;
import org.beizix.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserView;
import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.config.application.util.CommonUtil;
import org.beizix.core.config.application.util.MessageUtil;
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

  @Value("${app.admin.password.validity.period.days}")
  private long passwordValidPeriodDays;

  @Value("${app.admin.password.change.notice.period.days}")
  private long passwordChangeNoticeDays;

  @Value("${app.admin.session.validity.period.seconds}")
  private long sessionValidPeriodSeconds;

  @Value("${app.admin.session.maximum.num}")
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
          LoggedInUserSaveCmd.builder()
              .loggedInUserId(
                  LoggedInUserIdSaveCmd.builder()
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
    LoggedInUserView loggedInUser =
        loggedInUserViewPortIn.connect(new LoggedInUserIdCmd(AppType.ADMIN, username));
    if (loggedInUser == null) return false;

    LocalDateTime lastLoggedInAt = loggedInUser.getLastLoggedInAt();

    return lastLoggedInAt.plusSeconds(sessionValidPeriodSeconds).isAfter(LocalDateTime.now())
        && !currentIP.equals(loggedInUser.getClientIP());
  }
}
