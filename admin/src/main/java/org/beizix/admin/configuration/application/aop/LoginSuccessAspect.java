package org.beizix.admin.configuration.application.aop;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.beizix.core.application.domain.operationlog.model.save.OperationLogInput;
import org.beizix.core.application.port.in.operationlog.OperationLogSavePortIn;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.configuration.application.enums.OperationLogType;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.MessageUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoginSuccessAspect {
  private final OperationLogSavePortIn operationLogSavePortIn;
  private final CommonUtil commonUtil;
  private final MessageUtil messageUtil;

  @After("@annotation(org.beizix.admin.configuration.application.aop.LoginSuccessOperateLog)")
  public void operate(JoinPoint joinPoint) throws Throwable {
    Object[] args = joinPoint.getArgs();

    HttpServletRequest request = (HttpServletRequest) args[0];
    Authentication authentication = (Authentication) args[2];

    operationLogSavePortIn.connect(
        new OperationLogInput(
            AppType.ADMIN,
            OperationLogType.LOGIN_SUCCESS,
            null,
            commonUtil.getClientIP(request),
            messageUtil.getMessage("operate.login.success", authentication.getName())));
  }
}
