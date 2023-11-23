package org.beizix.admin.usecases.uicode.save.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecases.uicode.save.application.port.out.UICodeSavePortOut;
import org.beizix.admin.usecases.uicode.view.application.port.in.UICodeViewPortIn;
import org.beizix.core.application.port.out.uicode.UICodeMaxOrderNoPortOut;
import org.beizix.core.config.exception.AlreadyExistItemException;
import org.beizix.utility.common.MessageUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UICodeSaveService implements UICodeSavePortIn {
  private final UICodeSavePortOut createUpdateDao;
  private final UICodeMaxOrderNoPortOut maxOrderNoDao;
  private final UICodeViewPortIn uiCodeViewPortIn;
  private final MessageUtil messageUtil;

  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public String connect(UICodeSaveCommand createCommand, boolean isUpdate) {
    if (isUpdate)
      uiCodeViewPortIn
          .connect(createCommand.getId())
          .ifPresent(
              item -> {
                throw new AlreadyExistItemException(
                    messageUtil.getMessage("valid.common.already.exists", "ID"));
              });

    if (createCommand.getOrderNo() == null)
      createCommand.setOrderNo(maxOrderNoDao.connect(createCommand.getParentId()).orElse(-1) + 1);

    return createUpdateDao.connect(createCommand);
  }
}
