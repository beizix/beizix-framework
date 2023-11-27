package org.beizix.admin.usecase.privilege.save.application.port.in;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.view.application.domain.PrivilegeView;
import org.beizix.admin.usecase.privilege.view.application.port.out.PrivilegeViewPortOut;import org.beizix.security.application.domain.privilege.model.save.PrivilegeSaveInput;
import org.beizix.admin.usecase.privilege.save.application.port.out.PrivilegeNextOrderNoPortOut;
import org.beizix.admin.usecase.privilege.save.application.port.out.PrivilegeSavePortOut;
import org.beizix.security.config.exceptions.AlreadyExistsRoleException;
import org.beizix.utility.common.MessageUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeSaveService implements PrivilegeSavePortIn {
  private final PrivilegeSavePortOut portOut;
  private final PrivilegeViewPortOut<PrivilegeView> viewPortOut;
  private final PrivilegeNextOrderNoPortOut nextOrderNoPortOut;
  private final MessageUtil messageUtil;

  @Override
  public String connect(PrivilegeSaveInput saveInput) {
    // create 일 때 중복 여부 확인
    if (saveInput.getOrderNo() == null) {
      Optional.ofNullable(viewPortOut.connect(saveInput.getId()))
          .ifPresent(
              privilegeOutput -> {
                throw new AlreadyExistsRoleException(
                    messageUtil.getMessage("valid.common.already.exists", "ID"));
              });
      saveInput.setOrderNo(nextOrderNoPortOut.connect());
    }

    return portOut.connect(saveInput);
  }
}
