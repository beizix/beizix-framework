package org.beizix.core.adapter.persistence.uicode;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.uicode.UICodeMaxOrderNoPortOut;
import org.beizix.core.adapter.persistence.uicode.repository.UICodeMaxOrderNoRepo;

@Repository
@RequiredArgsConstructor
class UICodeMaxDao implements UICodeMaxOrderNoPortOut {
  private final UICodeMaxOrderNoRepo uiCodeMaxOrderNoRepo;

  @Override
  public Optional<Integer> connect(String parentId) {
    return uiCodeMaxOrderNoRepo.operate(parentId);
  }
}
