package org.beizix.admin.config.aop;

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
import org.beizix.core.application.domain.operationlog.model.OperationLog;
import org.beizix.core.application.port.in.operationlog.OperationLogSavePortIn;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.enums.OperationLogType;
import org.beizix.security.application.domain.admin.model.view.AdminViewOutput;
import org.beizix.security.application.domain.admin.model.view.RoleOutput;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.PropertyUtil;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Aspect
@Component
@RequiredArgsConstructor
public class RoleUpdateAspect {
  private final AdminViewPortIn adminViewPortIn;
  private final OperationLogSavePortIn operationLogSavePortIn;
  private final CommonUtil commonUtil;

  @Around("execution(* org.beizix.security.application.domain.admin.AdminSaveService.connect(..))")
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

    Optional<AdminViewOutput> beforeAdmin = adminViewPortIn.connect(adminId);

    //    List<String> currentRoles =
    //        adminId.getWithRoles().stream()
    //            .map(adminUserWithRole -> adminUserWithRole.getRole().getId())
    //            .collect(Collectors.toList());

    boolean createLog = false;

    if (beforeAdmin.isPresent()) {
      List<String> beforeRoles =
          beforeAdmin.get().getRoles().stream().map(RoleOutput::getId).collect(Collectors.toList());
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
          OperationLog.builder()
              .appType(AppType.ADMIN)
              .operationLogType(OperationLogType.ROLE_UPDATE)
              .operatorId(operatorId)
              .targetId(adminId)
              .ip(request != null ? commonUtil.getClientIP(request) : null)
              .taskDesc(
                  String.format(
                      "`%s` grants [%s] permissions to `%s`",
                      operatorId, StringUtils.join(currentRoles, ", "), adminId))
              .build());
    }

    return joinPoint.proceed();
  }
}
