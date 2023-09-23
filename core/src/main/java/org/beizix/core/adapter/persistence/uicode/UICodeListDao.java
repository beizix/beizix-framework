package org.beizix.core.adapter.persistence.uicode;

import java.util.List;
import java.util.stream.Collectors;

import org.beizix.core.application.domain.uicode.model.UICodeInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.uicode.UICodeListPortOut;
import org.beizix.core.adapter.persistence.uicode.repository.UICodeListRepo;
import org.beizix.core.adapter.persistence.uicode.model.UICode;

@Repository
@RequiredArgsConstructor
class UICodeListDao implements UICodeListPortOut {
  private final UICodeListRepo uiCodeListRepo;

  @Override
  public List<UICodeInput> connect(String parentId) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    modelMapper
        .typeMap(UICode.class, UICodeInput.class)
        .addMappings(mapper -> mapper.skip(UICodeInput::setNodes));

    return uiCodeListRepo.operate(parentId).stream()
        .map(item -> modelMapper.map(item, UICodeInput.class))
        .collect(Collectors.toList());
  }
}
