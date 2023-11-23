package org.beizix.admin.usecases.uicode.view.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecases.uicode.view.application.port.out.UICodeViewPortOut;
import org.beizix.admin.usecases.uicode.view.domain.UICodeView;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class UICodeViewDao implements UICodeViewPortOut {
  private final UICodeViewRepo uiCodeViewRepo;

  @Override
  public UICodeView connect(String id) {
    return uiCodeViewRepo
        .findById(id)
        .map(
            entity ->
                new UICodeView(
                    entity.getId(),
                    entity.getParentId(),
                    entity.getOrderNo(),
                    entity.getCodeText(),
                    entity.getCodeValue(),
                    entity.getMsgCode(),
                    entity.getInUse()))
        .orElse(null);
  }
}
