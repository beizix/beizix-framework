package app.module.admin.usecase.uicode.save.adapter.persistence;

import java.util.Optional;

import app.module.admin.usecase.uicode.save.application.port.out.UICodeMaxOrderNoPortOut;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
class UICodeMaxOrderNoDao implements UICodeMaxOrderNoPortOut {
  private final UICodeMaxOrderNoRepo uiCodeMaxOrderNoRepo;

  @Override
  public Optional<Integer> connect(String parentId) {
    return uiCodeMaxOrderNoRepo.operate(parentId);
  }
}
