package app.module.admin.usecase.uicode.view.adapter.persistence;

import app.module.admin.usecase.uicode.view.application.domain.UICodeView;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uicode.view.application.port.out.UICodeViewPortOut;
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
