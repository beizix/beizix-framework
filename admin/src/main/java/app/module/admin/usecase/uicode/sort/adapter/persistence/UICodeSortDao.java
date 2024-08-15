package app.module.admin.usecase.uicode.sort.adapter.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uicode.sort.application.port.out.UICodeSortPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class UICodeSortDao implements UICodeSortPortOut {
  private final UICodeSortRepo uiCodeSortRepo;

  @Override
  public void connect(String id, Integer orderNo) {
    uiCodeSortRepo.operate(id, orderNo);
  }
}
