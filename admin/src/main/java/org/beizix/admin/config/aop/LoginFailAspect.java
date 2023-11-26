package org.beizix.admin.config.aop;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.beizix.core.application.domain.operationlog.model.save.OperationLogInput;
import org.beizix.core.application.port.in.operationlog.OperationLogSavePortIn;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.configuration.application.enums.OperationLogType;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.MessageUtil;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginFailAspect {
  private final OperationLogSavePortIn operationLogSavePortIn;
  private final CommonUtil commonUtil;
  private final MessageUtil messageUtil;

  @After("@annotation(org.beizix.admin.config.aop.LoginFailOperateLog)")
  public void operate(JoinPoint joinPoint) throws Throwable {
    Object[] args = joinPoint.getArgs();
    HttpServletRequest request = (HttpServletRequest) args[0];
    String targetId = request.getParameter("username");

    operationLogSavePortIn.connect(
        new OperationLogInput(
            AppType.ADMIN,
            OperationLogType.LOGIN_FAIL,
            targetId,
            commonUtil.getClientIP(request),
            messageUtil.getMessage("operate.login.fail", targetId)));
  }
}
