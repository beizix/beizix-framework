package org.beizix.admin.config.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
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
        OperationLog.builder()
            .appType(AppType.ADMIN)
            .operationLogType(OperationLogType.LOGIN_FAIL)
            .operatorId("anonymous")
            .targetId(targetId)
            .ip(commonUtil.getClientIP(request))
            .taskDesc(messageUtil.getMessage("operate.login.fail", targetId))
            .build());
  }
}
