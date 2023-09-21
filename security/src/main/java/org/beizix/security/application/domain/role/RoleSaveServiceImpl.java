package org.beizix.security.application.domain.role;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.role.model.save.RoleSaveReq;
import org.beizix.security.application.port.in.role.RoleSaveService;
import org.beizix.security.application.port.in.role.RoleViewService;
import org.beizix.security.application.port.out.role.RoleNextOrderNoDao;
import org.beizix.security.application.port.out.role.RoleSaveDao;
import org.beizix.security.config.exceptions.AlreadyExistsRoleException;
import org.beizix.utility.common.MessageUtil;
import org.beizix.utility.enums.OperationType;

@Service
@RequiredArgsConstructor
public class RoleSaveServiceImpl implements RoleSaveService {
  private final RoleSaveDao roleSaveDao;
  private final RoleViewService roleViewService;
  private final RoleNextOrderNoDao roleNextOrderNoDao;
  private final MessageUtil messageUtil;

  @Override
  public RoleSaveReq operate(RoleSaveReq saveReq) {
    // create 일 때 중복여부 확인
    if (OperationType.CREATE.equals(saveReq.getOperationType())) {
      Optional.ofNullable(roleViewService.operate(saveReq.getId()))
          .ifPresent(
              adminUserRole1 -> {
                throw new AlreadyExistsRoleException(
                    messageUtil.getMessage("valid.common.already.exists", "ID"));
              });
      saveReq.setOrderNo(roleNextOrderNoDao.getNextOrderNo());
    }

    return roleSaveDao.operate(saveReq);
  }
}
