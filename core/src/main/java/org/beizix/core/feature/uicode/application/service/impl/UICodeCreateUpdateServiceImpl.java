package org.beizix.core.feature.uicode.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.config.exception.AlreadyExistItemException;
import org.beizix.core.feature.uicode.application.model.UICode;
import org.beizix.core.feature.uicode.application.service.UICodeCreateUpdateService;
import org.beizix.core.feature.uicode.application.service.UICodeViewService;
import org.beizix.core.feature.uicode.persistence.dao.UICodeCreateUpdateDao;
import org.beizix.core.feature.uicode.persistence.dao.UICodeMaxOrderNoDao;
import org.beizix.utility.common.MessageUtil;

@Service
@RequiredArgsConstructor
class UICodeCreateUpdateServiceImpl implements UICodeCreateUpdateService {
  private final UICodeCreateUpdateDao createUpdateDao;
  private final UICodeMaxOrderNoDao maxOrderNoDao;
  private final UICodeViewService uiCodeViewService;
  private final MessageUtil messageUtil;

  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public UICode operate(UICode uiCode, boolean checkDuplicate) {
    if (checkDuplicate)
      uiCodeViewService
          .operate(uiCode.getId())
          .ifPresent(
              item -> {
                throw new AlreadyExistItemException(
                    messageUtil.getMessage("valid.common.already.exists", "ID"));
              });

    if (uiCode.getOrderNo() == null)
      uiCode.setOrderNo(maxOrderNoDao.operate(uiCode.getParentId()).orElse(-1) + 1);
    return createUpdateDao.operate(uiCode);
  }
}
