package app.module.admin.config.application.security;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import app.module.admin.usecase.admin.status.ports.AdminUpdateAccountLockPortIn;
import app.module.admin.usecase.admin.status.ports.AdminUpdateLoginFailPortIn;
import app.module.admin.usecase.admin.view.ports.AdminViewPortIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.enums.OperationLogType;
import app.module.core.config.application.util.CommonUtil;
import app.module.core.config.application.util.MessageUtil;
import app.module.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;
import app.module.core.usecase.operationlog.save.application.port.in.OperationLogSavePortIn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminAuthFailEvent {
  @Value("${app.admin.auth.fail.permit}")
  private int failPermit;

  private final AdminViewPortIn adminViewPortIn;
  private final AdminUpdateLoginFailPortIn updateLoginFailPortIn;
  private final AdminUpdateAccountLockPortIn updateAccountLockPortIn;
  private final CommonUtil commonUtil;
  private final OperationLogSavePortIn operationLogSavePortIn;
  private final MessageUtil messageUtil;

  @EventListener
  public void handleAuthenticationFailureEvent(AbstractAuthenticationFailureEvent event) {
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    if (event.getException() instanceof BadCredentialsException) {
      adminViewPortIn
          .connect(request.getParameter("username"))
          .filter(admin -> admin.getAccountLocked() == null || !admin.getAccountLocked())
          .ifPresent(
              adminUserViewInfo -> {
                String adminUserId = adminUserViewInfo.getId();
                int failCnt =
                    Optional.ofNullable(adminUserViewInfo.getLoginFailCnt()).orElse(0) + 1;
                updateLoginFailPortIn.connect(adminUserId, failCnt);

                if (failCnt >= failPermit) {
                  // 계정 잠금 처리
                  updateAccountLockPortIn.connect(adminUserId, true);

                  // 계정 잠금 메세지 전달
                  commonUtil.addFlashAlertMessages(
                      request.getSession(),
                      messageUtil.getMessage("security.auth.fail.exceed", failPermit));

                  // 계정 잠금 로그 기록
                  operationLogSavePortIn.connect(
                      new OperationLogSaveCommand(
                          AppType.ADMIN,
                          OperationLogType.ACCOUNT_LOCKED,
                          adminUserViewInfo.getId(),
                          commonUtil.getClientIP(request),
                          messageUtil.getMessage(
                              "operate.account.locked", adminUserViewInfo.getId())));
                }
              });
    }
  }
}
