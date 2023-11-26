package org.beizix.admin.usecase.uicode.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uicode.save.application.port.in.UICodeSaveCommand;
import org.beizix.admin.usecase.uicode.save.application.port.out.UICodeSavePortOut;
import org.beizix.core.configuration.adapter.persistence.UICode;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class UICodeSaveDao implements UICodeSavePortOut {
  private final UICodeSaveRepo uiCodeSaveRepo;

  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public String connect(UICodeSaveCommand saveCommand) {
    UICode entity =
        uiCodeSaveRepo.save(
            new UICode(
                saveCommand.getId(),
                saveCommand.getParentId(),
                saveCommand.getOrderNo(),
                saveCommand.getCodeText(),
                saveCommand.getCodeValue(),
                saveCommand.getMsgCode(),
                saveCommand.getInUse(),
                null));

    return entity.getId();
  }
}
