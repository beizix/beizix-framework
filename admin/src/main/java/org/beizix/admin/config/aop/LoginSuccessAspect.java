package org.beizix.admin.config.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.MessageUtil;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.enums.OperationLogType;
import org.beizix.core.application.domain.operationLog.model.OperationLog;
import org.beizix.core.application.port.in.operationLog.OperationLogSavePortIn;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoginSuccessAspect {
  private final OperationLogSavePortIn operationLogSavePortIn;
  private final CommonUtil commonUtil;
  private final MessageUtil messageUtil;

  @After("@annotation(org.beizix.admin.config.aop.LoginSuccessOperateLog)")
  public void operate(JoinPoint joinPoint) throws Throwable {
    Object[] args = joinPoint.getArgs();

    HttpServletRequest request = (HttpServletRequest) args[0];
    Authentication authentication = (Authentication) args[2];

    operationLogSavePortIn.connect(
        OperationLog.builder()
            .appType(AppType.ADMIN)
            .operationLogType(OperationLogType.LOGIN_SUCCESS)
            .operatorId(authentication.getName())
            .ip(commonUtil.getClientIP(request))
            .taskDesc(messageUtil.getMessage("operate.login.success", authentication.getName()))
            .build());
  }
}
