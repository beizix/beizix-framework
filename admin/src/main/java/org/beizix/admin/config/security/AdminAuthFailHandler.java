package org.beizix.admin.config.security;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.beizix.admin.config.aop.LoginFailOperateLog;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.enums.OperationLogType;
import org.beizix.core.application.domain.operationLog.model.OperationLog;
import org.beizix.core.application.port.in.operationLog.OperationLogSavePortIn;
import org.beizix.security.application.port.in.admin.AdminSavePortIn;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.MessageUtil;

@Component
@RequiredArgsConstructor
public class AdminAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
  private final AdminViewPortIn adminViewPortIn;
  private final AdminSavePortIn adminSavePortIn;
  private final CommonUtil commonUtil;
  private final OperationLogSavePortIn operationLogSavePortIn;
  private final MessageUtil messageUtil;

  @Value("${org.beizix.admin.auth.fail.permit}")
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
                adminSavePortIn.updateLoginFailCnt(adminUserId, failCnt);

                if (failCnt >= failPermit) {
                  // 계정 잠금 처리
                  adminSavePortIn.updateAccountLocked(adminUserId, true);

                  // 계정 잠금 메세지 전달
                  commonUtil.addFlashAlertMessages(
                      request.getSession(),
                      messageUtil.getMessage("org.beizix.auth.fail.exceed", failPermit));

                  // 계정 잠금 로그 기록
                  operationLogSavePortIn.connect(
                      OperationLog.builder()
                          .appType(AppType.ADMIN)
                          .operationLogType(OperationLogType.ACCOUNT_LOCKED)
                          .operatorId("anonymous")
                          .targetId(adminUserViewInfo.getId())
                          .ip(commonUtil.getClientIP(request))
                          .taskDesc(
                              messageUtil.getMessage(
                                  "operate.account.locked", adminUserViewInfo.getId()))
                          .build());
                }
              });
    }

    setDefaultFailureUrl("/login?error");
    super.onAuthenticationFailure(request, response, exception);
  }
}
