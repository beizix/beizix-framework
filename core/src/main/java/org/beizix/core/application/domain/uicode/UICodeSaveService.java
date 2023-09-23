package org.beizix.core.application.domain.uicode;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.config.exception.AlreadyExistItemException;
import org.beizix.core.application.domain.uicode.model.UICodeInput;
import org.beizix.core.application.port.in.uicode.UICodeSavePortIn;
import org.beizix.core.application.port.in.uicode.UICodeViewPortIn;
import org.beizix.core.application.port.out.uicode.UICodeSavePortOut;
import org.beizix.core.application.port.out.uicode.UICodeMaxOrderNoPortOut;
import org.beizix.utility.common.MessageUtil;

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
  public UICodeInput connect(UICodeInput uiCodeInput, boolean checkDuplicate) {
    if (checkDuplicate)
      uiCodeViewPortIn
          .connect(uiCodeInput.getId())
          .ifPresent(
              item -> {
                throw new AlreadyExistItemException(
                    messageUtil.getMessage("valid.common.already.exists", "ID"));
              });

    if (uiCodeInput.getOrderNo() == null)
      uiCodeInput.setOrderNo(maxOrderNoDao.connect(uiCodeInput.getParentId()).orElse(-1) + 1);
    return createUpdateDao.connect(uiCodeInput);
  }
}
