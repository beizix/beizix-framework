package org.beizix.core.adapter.persistence.uicode;

import org.beizix.core.application.domain.uicode.model.UICodeInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.uicode.UICodeHierarchyPortOut;
import org.beizix.core.adapter.persistence.uicode.repository.UICodeHierarchyRepo;
import org.beizix.core.adapter.persistence.uicode.model.UICode;

@Repository
@RequiredArgsConstructor
class UICodeHierarchyDao implements UICodeHierarchyPortOut {
  private final UICodeHierarchyRepo uiCodeHierarchyRepo;
  private final ModelMapper modelMapper;

  @Override
  public UICodeInput connect() {
    UICode topNode = uiCodeHierarchyRepo.findByParentIdIsNull();
    return modelMapper.map(topNode, UICodeInput.class);
  }
}
