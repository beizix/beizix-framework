package org.beizix.core.feature.uicode.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.uicode.application.service.UICodeViewService;
import org.beizix.core.feature.uicode.persistence.dao.UICodeViewDao;
import org.beizix.core.feature.uicode.application.model.UICode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class UICodeViewServiceImpl implements UICodeViewService {
  private final UICodeViewDao uiCodeViewDao;

  @Override
  public Optional<UICode> operate(String id) {
    return Optional.ofNullable(uiCodeViewDao.operate(id));
  }
}
