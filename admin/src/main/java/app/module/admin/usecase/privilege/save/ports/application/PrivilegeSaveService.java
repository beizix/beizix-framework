package app.module.admin.usecase.privilege.save.ports.application;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.privilege.save.ports.PrivilegeSavePortIn;
import app.module.admin.usecase.privilege.view.ports.application.domain.PrivilegeView;
import app.module.admin.usecase.privilege.view.ports.PrivilegeViewPortOut;import app.module.admin.usecase.admin.save.ports.application.domain.PrivilegeSaveCommand;
import app.module.admin.usecase.privilege.save.ports.PrivilegeNextOrderNoPortOut;
import app.module.admin.usecase.privilege.save.ports.PrivilegeSavePortOut;
import app.module.admin.config.application.exception.AlreadyExistsRoleException;
import app.module.core.config.application.util.MessageUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeSaveService implements PrivilegeSavePortIn {
  private final PrivilegeSavePortOut portOut;
  private final PrivilegeViewPortOut<PrivilegeView> viewPortOut;
  private final PrivilegeNextOrderNoPortOut nextOrderNoPortOut;
  private final MessageUtil messageUtil;

  @Override
  public String connect(PrivilegeSaveCommand saveInput) {
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
