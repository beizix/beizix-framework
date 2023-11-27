package org.beizix.admin.usecase.admin.save.application.port.in;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.view.application.domain.AdminView;
import org.beizix.admin.usecase.admin.view.application.port.in.AdminViewPortIn;
import org.beizix.admin.usecase.admin.save.application.port.out.AdminSavePortOut;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AdminSaveService implements AdminSavePortIn {
  private final AdminSavePortOut adminSavePortOut;
  private final BCryptPasswordEncoder passwordEncoder;
  private final AdminViewPortIn adminViewPortIn;

  @Override
  public String connect(
      String id,
      String password,
      String email,
      Boolean accountDisabled,
      Boolean accountLocked,
      List<String> roleIds) {

    LocalDateTime passwordUpdatedAt;
    Integer loginFailCnt;

    AdminView viewOutput = adminViewPortIn.connect(id).orElse(null);

    if (viewOutput != null) {
      if (!StringUtils.isEmptyOrWhitespace(password)) {
        password = passwordEncoder.encode(password);
        passwordUpdatedAt = LocalDateTime.now();
        loginFailCnt = viewOutput.getLoginFailCnt();
      } else {
        password = viewOutput.getPassword();
        passwordUpdatedAt = viewOutput.getPasswordUpdatedAt();
        loginFailCnt = null;
      }
    } else {
      password = passwordEncoder.encode(password);
      passwordUpdatedAt = LocalDateTime.now();
      loginFailCnt = null;
    }

    return adminSavePortOut.connect(
        id,
        password,
        passwordUpdatedAt,
        email,
        accountDisabled,
        loginFailCnt,
        accountLocked,
        roleIds);
  }
}
