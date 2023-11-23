package org.beizix.admin.usecases.uicode.remove.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecases.uicode.remove.application.port.out.UICodeRemovePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class UICodeRemoveDao implements UICodeRemovePortOut {

  private final UICodeRemoveRepo uiCodeRemoveRepo;

  @Override
  public void connect(String id) {
    uiCodeRemoveRepo.deleteById(id);
  }
}
