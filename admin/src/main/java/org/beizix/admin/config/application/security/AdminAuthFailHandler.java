package org.beizix.admin.config.application.security;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.config.application.aop.LoginFailOperateLog;
import org.beizix.admin.usecase.admin.status.ports.AdminUpdateAccountLockPortIn;
import org.beizix.admin.usecase.admin.status.ports.AdminUpdateLoginFailPortIn;
import org.beizix.admin.usecase.admin.view.ports.AdminViewPortIn;
import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.config.application.enums.OperationLogType;
import org.beizix.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;
import org.beizix.core.usecase.operationlog.save.application.port.in.OperationLogSavePortIn;
import org.beizix.core.config.application.util.CommonUtil;
import org.beizix.core.config.application.util.MessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
  private final AdminViewPortIn adminViewPortIn;
  private final AdminUpdateLoginFailPortIn updateLoginFailPortIn;
  private final AdminUpdateAccountLockPortIn updateAccountLockPortIn;
  private final CommonUtil commonUtil;
  private final OperationLogSavePortIn operationLogSavePortIn;
  private final MessageUtil messageUtil;

  @Value("${app.admin.auth.fail.permit}")
  private int failPermit;

  @Override
  @LoginFailOperateLog
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {

    if (exception instanceof BadCredentialsException) {

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
                      messageUtil.getMessage("org.beizix.auth.fail.exceed", failPermit));

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

    setDefaultFailureUrl("/login?error");
    super.onAuthenticationFailure(request, response, exception);
  }
}
