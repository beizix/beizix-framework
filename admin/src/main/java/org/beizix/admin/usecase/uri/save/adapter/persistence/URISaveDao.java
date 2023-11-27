package org.beizix.admin.usecase.uri.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uri.save.application.port.out.URISavePortOut;
import org.beizix.core.configuration.adapter.persistence.entity.URI;
import org.beizix.core.application.domain.uri.model.save.URIInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class URISaveDao implements URISavePortOut {
  private final URISaveRepo uriRepo;
  private final ModelMapper modelMapper;

  @Override
  public String connect(URIInput uri) {
    URI createdItem = uriRepo.save(modelMapper.map(uri, URI.class));
    return createdItem.getId();
  }
}
