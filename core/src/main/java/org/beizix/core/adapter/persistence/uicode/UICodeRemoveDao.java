package org.beizix.core.adapter.persistence.uicode;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.uicode.UICodeRemovePortOut;
import org.beizix.core.adapter.persistence.uicode.repository.UICodeRemoveRepo;

@Repository
@RequiredArgsConstructor
class UICodeRemoveDao implements UICodeRemovePortOut {
  private final UICodeRemoveRepo uiCodeRemoveRepo;

  @Override
  public void connect(String id) {
    uiCodeRemoveRepo.deleteById(id);
  }
}
