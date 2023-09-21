package org.beizix.security.application.domain.admin;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import org.beizix.security.application.domain.admin.model.save.AdminSaveReq;
import org.beizix.security.application.domain.admin.model.view.WithRoleResp;
import org.beizix.security.application.port.in.admin.AdminSaveService;
import org.beizix.security.application.port.in.admin.AdminViewService;
import org.beizix.security.application.port.in.admin_role.AdminWithRoleRemoveService;
import org.beizix.security.application.port.out.admin.AdminSaveDao;

@Service
@RequiredArgsConstructor
public class AdminSaveServiceImpl implements AdminSaveService {
  private final AdminSaveDao adminSaveDao;
  private final BCryptPasswordEncoder passwordEncoder;
  private final AdminViewService adminViewService;
  private final AdminWithRoleRemoveService adminWithRoleRemoveService;

  @Override
  public AdminSaveReq operate(AdminSaveReq saveReq) {

    adminViewService
        .operate(saveReq.getId())
        .ifPresentOrElse(
            retrieveUser -> {
              adminWithRoleRemoveService.operateAll(
                  retrieveUser.getWithRoles().stream()
                      .map(WithRoleResp::getId)
                      .collect(Collectors.toSet()));

              if (!StringUtils.isEmptyOrWhitespace(saveReq.getUpdatePassword())) {
                saveReq.setPassword(passwordEncoder.encode(saveReq.getUpdatePassword()));
                saveReq.setPasswordUpdatedAt(LocalDateTime.now());
              } else {
                saveReq.setPassword(retrieveUser.getPassword());
                saveReq.setPasswordUpdatedAt(retrieveUser.getPasswordUpdatedAt());
              }
            },
            () -> {
              saveReq.setPassword(passwordEncoder.encode(saveReq.getPassword()));
              saveReq.setPasswordUpdatedAt(LocalDateTime.now());
            });

    return adminSaveDao.operate(saveReq).orElseThrow();
  }  

  @Override
  public void updateLoginFailCnt(String id, Integer failCnt) {
    adminSaveDao.updateLoginFailCnt(id, failCnt);
  }

  @Override
  public void updateAccountLocked(String id, boolean accountLocked) {
    adminSaveDao.updateAccountLocked(id, accountLocked);
  }
}
