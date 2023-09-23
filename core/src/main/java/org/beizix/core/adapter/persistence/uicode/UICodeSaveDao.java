package org.beizix.core.adapter.persistence.uicode;

import org.beizix.core.application.domain.uicode.model.UICodeInput;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.uicode.UICodeSavePortOut;
import org.beizix.core.adapter.persistence.uicode.repository.UICodeCreateUpdateRepo;
import org.beizix.core.adapter.persistence.uicode.model.UICode;

@Repository
@RequiredArgsConstructor
class UICodeSaveDao implements UICodeSavePortOut {
  private final UICodeCreateUpdateRepo uiCodeCreateUpdateRepo;
  private final ModelMapper modelMapper;

  @CacheEvict(
      value = {"UICodeHierarchyCache", "ChildUICodesByParentIdCache"},
      allEntries = true)
  @Override
  public UICodeInput connect(
      UICodeInput uiCodeInput) {
    UICode uiCodeEntity =
        uiCodeCreateUpdateRepo.save(modelMapper.map(uiCodeInput, UICode.class));
    return modelMapper.map(uiCodeEntity, UICodeInput.class);
  }
}
