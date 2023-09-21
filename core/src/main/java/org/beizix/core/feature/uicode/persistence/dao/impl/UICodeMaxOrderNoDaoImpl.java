package org.beizix.core.feature.uicode.persistence.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.uicode.persistence.dao.UICodeMaxOrderNoDao;
import org.beizix.core.feature.uicode.persistence.dao.impl.repository.UICodeMaxOrderNoRepo;

@Repository
@RequiredArgsConstructor
class UICodeMaxOrderNoDaoImpl implements UICodeMaxOrderNoDao {
  private final UICodeMaxOrderNoRepo uiCodeMaxOrderNoRepo;

  @Override
  public Optional<Integer> operate(String parentId) {
    return uiCodeMaxOrderNoRepo.operate(parentId);
  }
}
