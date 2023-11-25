package org.beizix.core.usecase.uicode.list.adapter.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.beizix.core.usecase.uicode.list.domain.UICodeElement;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.uicode.list.application.port.out.UICodeListPortOut;
import org.beizix.core.adapter.persistence.uicode.model.UICode;

@Repository
@RequiredArgsConstructor
class UICodeListDao implements UICodeListPortOut {
  private final UICodeListRepo uiCodeListRepo;

  @Override
  public List<UICodeElement> connect(String parentId) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    modelMapper
        .typeMap(UICode.class, UICodeElement.class)
        .addMappings(mapper -> mapper.skip(UICodeElement::setNodes));

    return uiCodeListRepo.operate(parentId).stream()
        .map(item -> modelMapper.map(item, UICodeElement.class))
        .collect(Collectors.toList());
  }
}
