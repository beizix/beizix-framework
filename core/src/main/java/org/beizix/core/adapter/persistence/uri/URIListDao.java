package org.beizix.core.adapter.persistence.uri;

import java.util.List;
import java.util.stream.Collectors;

import org.beizix.core.adapter.persistence.uri.repository.URIListRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.domain.uri.model.URIInput;
import org.beizix.core.application.port.out.uri.URIListPortOut;

@Repository
@RequiredArgsConstructor
class URIListDao implements URIListPortOut {
  private final URIListRepo uriListRepo;
  private final ModelMapper modelMapper;

  @Override
  public List<URIInput> connect(AppType appType) {
    return uriListRepo.findAllByAppType(appType).stream()
        .map(uriEntity -> modelMapper.map(uriEntity, URIInput.class))
        .collect(Collectors.toList());
  }
}
