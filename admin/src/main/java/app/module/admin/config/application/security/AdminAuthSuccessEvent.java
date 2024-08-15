package app.module.admin.config.application.security;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;

import app.module.admin.usecase.admin.status.ports.AdminUpdateLoginFailPortIn;
import app.module.admin.usecase.admin.view.ports.AdminViewPortIn;
import app.module.admin.usecase.loggedinuser.save.ports.LoggedInUserSavePortIn;
import app.module.admin.usecase.loggedinuser.view.ports.LoggedInUserViewPortIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.module.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserIdSaveCmd;
import app.module.admin.usecase.loggedinuser.save.ports.application.domain.LoggedInUserSaveCmd;
import app.module.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserIdCmd;
import app.module.admin.usecase.loggedinuser.view.ports.application.domain.LoggedInUserView;
import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.util.CommonUtil;
import app.module.core.config.application.util.MessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminAuthSuccessEvent {
  @Value("${app.admin.password.validity.period.days}")
  private long passwordValidPeriodDays;

  @Value("${app.admin.password.change.notice.period.days}")
  private long passwordChangeNoticeDays;

  @Value("${app.admin.session.validity.period.seconds}")
  private long sessionValidPeriodSeconds;

  @Value("${app.admin.session.maximum.num}")
  private Integer maxSessionNum;

  private final MessageUtil messageUtil;
  private final AdminViewPortIn adminViewPortIn;
  private final AdminUpdateLoginFailPortIn updateLoginFailPortIn;
  private final LoggedInUserViewPortIn loggedInUserViewPortIn;
  private final LoggedInUserSavePortIn loggedInUserSavePortIn;
  private final CommonUtil commonUtil;

  @EventListener
  public void handleAuthenticationSuccessEvent(AuthenticationSuccessEvent event) {
    Authentication authentication = event.getAuthentication();
    AdminUser adminUser = (AdminUser) authentication.getPrincipal();

    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    // 로그인 실패 회수 초기화
    adminViewPortIn
        .connect(authentication.getName())
        .ifPresent(admin -> updateLoginFailPortIn.connect(admin.getId(), 0));

    // 패스워드 변경일 공지
    if (passwordValidPeriodDays != -1
        && adminUser.getRemainPasswordDays() <= passwordChangeNoticeDays) {
      commonUtil.addFlashAlertMessages(
          request.getSession(),
          messageUtil.getMessage(
              "org.beizix.password.change.notify", adminUser.getRemainPasswordDays()));
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
  }

  @EventListener
  public void handleAuthenticationFailureEvent(AbstractAuthenticationFailureEvent event) {
    Exception e = event.getException();
    Authentication authentication = event.getAuthentication();
    log.warn("Unsuccessful authentication result: {}", authentication, e);
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
