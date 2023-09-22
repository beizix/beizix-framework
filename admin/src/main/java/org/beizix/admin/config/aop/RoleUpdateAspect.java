package org.beizix.admin.config.aop;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.enums.OperationLogType;
import org.beizix.core.feature.operationlog.application.model.OperationLog;
import org.beizix.core.feature.operationlog.application.service.OperationLogCreateService;
import org.beizix.security.application.domain.admin.model.save.AdminSaveInput;
import org.beizix.security.application.domain.admin.model.view.AdminViewOutput;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.PropertyUtil;

@Aspect
@Component
@RequiredArgsConstructor
public class RoleUpdateAspect {
  private final AdminViewPortIn adminViewPortIn;
  private final OperationLogCreateService operationLogCreateService;
  private final CommonUtil commonUtil;

  @Around(
      "execution(* org.beizix.security.application.domain.admin.AdminSaveService.connect(..))")
  public Object operate(ProceedingJoinPoint joinPoint) throws Throwable {
    if (PropertyUtil.isAdminSingleRole()) return joinPoint.proceed();

    Object[] args = joinPoint.getArgs();

    AdminSaveInput admin = (AdminSaveInput) args[0];
    Optional<AdminViewOutput> beforeAdmin = adminViewPortIn.connect(admin.getId());

    List<String> currentRoles =
        admin.getWithRoles().stream()
            .map(adminUserWithRole -> adminUserWithRole.getRole().getId())
            .collect(Collectors.toList());

    boolean createLog = false;

    if (beforeAdmin.isPresent()) {
      List<String> beforeRoles =
          beforeAdmin.get().getWithRoles().stream()
              .map(adminUserWithRole -> adminUserWithRole.getRole().getId())
              .collect(Collectors.toList());
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

      operationLogCreateService.operate(
          OperationLog.builder()
              .appType(AppType.ADMIN)
              .operationLogType(OperationLogType.ROLE_UPDATE)
              .operatorId(operatorId)
              .targetId(admin.getId())
              .ip(request != null ? commonUtil.getClientIP(request) : null)
              .taskDesc(
                  String.format(
                      "`%s` grants [%s] permissions to `%s`",
                      operatorId, StringUtils.join(currentRoles, ", "), admin.getId()))
              .build());
    }

    return joinPoint.proceed();
  }
}
