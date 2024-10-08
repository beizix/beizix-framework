package app.module.core.usecase.uicode.list.adapter.persistence;

import java.util.List;
import java.util.stream.Collectors;

import app.module.core.usecase.uicode.list.application.domain.UICodeElement;
import app.module.core.usecase.uicode.list.application.port.out.UICodeListPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class UICodeListDao implements UICodeListPortOut {
  private final UICodeListRepo uiCodeListRepo;

  @Override
  public List<UICodeElement> connect(String parentId) {
    return uiCodeListRepo.operate(parentId).stream()
        .map(
            item ->
                new UICodeElement(
                    item.getId(),
                    item.getParentId(),
                    item.getOrderNo(),
                    item.getCodeText(),
                    item.getCodeValue(),
                    item.getMsgCode(),
                    item.getInUse()))
        .collect(Collectors.toList());
  }
}
