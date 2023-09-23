package org.beizix.core.adapter.persistence.uicode;

import org.beizix.core.application.domain.uicode.model.UICodeInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.uicode.UICodeViewPortOut;
import org.beizix.core.adapter.persistence.uicode.repository.UICodeViewRepo;
import org.beizix.core.adapter.persistence.uicode.model.UICode;

@Repository
@RequiredArgsConstructor
class UICodeViewDao implements UICodeViewPortOut {
  private final UICodeViewRepo uiCodeViewRepo;

  @Override
  public UICodeInput connect(String id) {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setAmbiguityIgnored(true);
    mapper
        .typeMap(UICode.class, UICodeInput.class)
        .addMappings(mapping -> mapping.skip(
            UICodeInput::setNodes));

    return uiCodeViewRepo
        .findById(id)
        .map(uiCodeEntity -> mapper.map(uiCodeEntity, UICodeInput.class))
        .orElse(null);
  }
}
