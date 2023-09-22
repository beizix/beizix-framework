package org.beizix.security.application.domain.admin;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import org.beizix.security.application.domain.admin.model.save.AdminSaveInput;
import org.beizix.security.application.domain.admin.model.view.WithRoleOutput;
import org.beizix.security.application.port.in.admin.AdminSavePortIn;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;
import org.beizix.security.application.port.in.admin_role.AdminWithRoleRemovePortIn;
import org.beizix.security.application.port.out.admin.AdminSavePortOut;

@Service
@RequiredArgsConstructor
public class AdminSaveService implements AdminSavePortIn {
  private final AdminSavePortOut adminSavePortOut;
  private final BCryptPasswordEncoder passwordEncoder;
  private final AdminViewPortIn adminViewPortIn;
  private final AdminWithRoleRemovePortIn adminWithRoleRemovePortIn;

  @Override
  public AdminSaveInput connect(AdminSaveInput saveReq) {

    adminViewPortIn
        .connect(saveReq.getId())
        .ifPresentOrElse(
            retrieveUser -> {
              adminWithRoleRemovePortIn.connect(
                  retrieveUser.getWithRoles().stream()
                      .map(WithRoleOutput::getId)
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

    return adminSavePortOut.connect(saveReq).orElseThrow();
  }  

  @Override
  public void updateLoginFailCnt(String id, Integer failCnt) {
    adminSavePortOut.updateLoginFailCnt(id, failCnt);
  }

  @Override
  public void updateAccountLocked(String id, boolean accountLocked) {
    adminSavePortOut.updateAccountLocked(id, accountLocked);
  }
}
