package org.beizix.core.adapter.persistence.uicode;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.uicode.UICodeSortPortOut;
import org.beizix.core.adapter.persistence.uicode.repository.UICodeSortRepo;

@Repository
@RequiredArgsConstructor
class UICodeSortDao implements UICodeSortPortOut {
  private final UICodeSortRepo uiCodeSortRepo;

  @Override
  public void connect(String id, Integer orderNo) {
    uiCodeSortRepo.operate(id, orderNo);
  }
}
