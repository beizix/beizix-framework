package org.beizix.admin.config.application.aop;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.beizix.admin.usecase.admin.view.ports.application.domain.AdminView;
import org.beizix.admin.usecase.admin.view.ports.application.domain.RoleView;
import org.beizix.admin.usecase.admin.view.ports.AdminViewPortIn;
import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.config.application.enums.OperationLogType;
import org.beizix.core.usecase.operationlog.save.application.domain.OperationLogSaveCommand;
import org.beizix.core.usecase.operationlog.save.application.port.in.OperationLogSavePortIn;
import org.beizix.core.config.application.util.CommonUtil;
import org.beizix.core.config.application.util.PropertyUtil;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Aspect
@Component
@RequiredArgsConstructor
public class RoleUpdateAspect {
  private final AdminViewPortIn adminViewPortIn;
  private final OperationLogSavePortIn operationLogSavePortIn;
  private final CommonUtil commonUtil;

  @Around(
      "execution(* org.beizix.admin.usecase.admin.save.ports.application.AdminSaveService.connect(..))")
  public Object operate(ProceedingJoinPoint joinPoint) throws Throwable {
    if (PropertyUtil.isAdminSingleRole()) return joinPoint.proceed();

    Object[] args = joinPoint.getArgs();

    String adminId = (String) args[0];
    List<String> currentRoles = Collections.emptyList();

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();

    for (int i = 0; i < method.getParameters().length; i++) {
      String parameterName = method.getParameters()[i].getName();
      if (parameterName.equals("roleIds") && args[i] instanceof List)
        currentRoles = (List<String>) args[i];
    }

    Optional<AdminView> beforeAdmin = adminViewPortIn.connect(adminId);

    //    List<String> currentRoles =
    //        adminId.getWithRoles().stream()
    //            .map(adminUserWithRole -> adminUserWithRole.getRole().getId())
    //            .collect(Collectors.toList());

    boolean createLog = false;

    if (beforeAdmin.isPresent()) {
      List<String> beforeRoles =
          beforeAdmin.get().getRoles().stream().map(RoleView::getId).collect(Collectors.toList());
      if (beforeRoles.size() != currentRoles.size()
          || currentRoles.stream().anyMatch(item -> !beforeRoles.contains(item))) {
        createLog = true;
      }
    } else if (currentRoles.size() > 0) {
      createLog = true;
    }

    if (createLog) {
      String operatorId = Optional.ofNullable(commonUtil.getLoginUsername()).orElse("init data");
      HttpServletRequest request = commonUtil.getRequest();

      operationLogSavePortIn.connect(
          new OperationLogSaveCommand(
              AppType.ADMIN,
              OperationLogType.ROLE_UPDATE,
              adminId,
              request != null ? commonUtil.getClientIP(request) : null,
              String.format(
                  "`%s` grants [%s] permissions to `%s`",
                  operatorId, StringUtils.join(currentRoles, ", "), adminId)));
    }

    return joinPoint.proceed();
  }
}
