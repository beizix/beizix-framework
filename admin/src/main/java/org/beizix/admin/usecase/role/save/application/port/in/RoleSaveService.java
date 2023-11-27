package org.beizix.admin.usecase.role.save.application.port.in;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.role.view.application.port.in.RoleViewPortIn;
import org.beizix.admin.usecase.role.save.application.port.out.RoleNextOrderNoPortOut;
import org.beizix.admin.usecase.role.save.application.port.out.RoleSavePortOut;
import org.beizix.security.config.exceptions.AlreadyExistsRoleException;
import org.beizix.utility.common.MessageUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleSaveService implements RoleSavePortIn {
  private final RoleSavePortOut roleSavePortOut;
  private final RoleViewPortIn roleViewPortIn;
  private final RoleNextOrderNoPortOut roleNextOrderNoPortOut;
  private final MessageUtil messageUtil;

  @Override
  public String connect(String id, String description, Integer orderNo, List<String> privilegeIds) {
    // create 일 때 중복여부 확인
    if (orderNo == null) {
      Optional.ofNullable(roleViewPortIn.connect(id))
          .ifPresent(
              adminUserRole1 -> {
                throw new AlreadyExistsRoleException(
                    messageUtil.getMessage("valid.common.already.exists", "ID"));
              });
      orderNo = roleNextOrderNoPortOut.connect();
    }

    return roleSavePortOut.connect(id, description, orderNo, privilegeIds);
  }
}
