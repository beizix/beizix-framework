package app.module.admin.config.application.aop;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.enums.OperationLogType;
import app.module.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;
import app.module.core.usecase.operationlog.save.application.port.in.OperationLogSavePortIn;
import app.module.core.config.application.util.CommonUtil;
import app.module.core.config.application.util.MessageUtil;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginFailAspect {
  private final OperationLogSavePortIn operationLogSavePortIn;
  private final CommonUtil commonUtil;
  private final MessageUtil messageUtil;

  @After("@annotation(app.module.admin.config.application.aop.LoginFailOperateLog)")
  public void operate(JoinPoint joinPoint) throws Throwable {
    Object[] args = joinPoint.getArgs();
    HttpServletRequest request = (HttpServletRequest) args[0];
    String targetId = request.getParameter("username");

    operationLogSavePortIn.connect(
        new OperationLogSaveCommand(
            AppType.ADMIN,
            OperationLogType.LOGIN_FAIL,
            targetId,
            commonUtil.getClientIP(request),
            messageUtil.getMessage("operate.login.fail", targetId)));
  }
}
