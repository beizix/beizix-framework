package org.beizix.security.application.domain.role;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.role.model.save.RoleSaveInput;
import org.beizix.security.application.port.in.role.RoleSavePortIn;
import org.beizix.security.application.port.in.role.RoleViewPortIn;
import org.beizix.security.application.port.out.role.RoleNextOrderNoPortOut;
import org.beizix.security.application.port.out.role.RoleSavePortOut;
import org.beizix.security.config.exceptions.AlreadyExistsRoleException;
import org.beizix.utility.common.MessageUtil;
import org.beizix.utility.enums.OperationType;

@Service
@RequiredArgsConstructor
public class RoleSaveService implements RoleSavePortIn {
  private final RoleSavePortOut roleSavePortOut;
  private final RoleViewPortIn roleViewPortIn;
  private final RoleNextOrderNoPortOut roleNextOrderNoPortOut;
  private final MessageUtil messageUtil;

  @Override
  public RoleSaveInput connect(RoleSaveInput saveReq) {
    // create 일 때 중복여부 확인
    if (OperationType.CREATE.equals(saveReq.getOperationType())) {
      Optional.ofNullable(roleViewPortIn.connect(saveReq.getId()))
          .ifPresent(
              adminUserRole1 -> {
                throw new AlreadyExistsRoleException(
                    messageUtil.getMessage("valid.common.already.exists", "ID"));
              });
      saveReq.setOrderNo(roleNextOrderNoPortOut.connect());
    }

    return roleSavePortOut.connect(saveReq);
  }
}
