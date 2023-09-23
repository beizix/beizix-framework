package org.beizix.core.adapter.persistence.uri;

import org.beizix.core.adapter.persistence.uri.repository.URIRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.uri.model.URIInput;
import org.beizix.core.application.port.out.uri.URISavePortOut;
import org.beizix.core.adapter.persistence.uri.model.URI;

@Repository
@RequiredArgsConstructor
public class URISaveDao implements URISavePortOut {
  private final URIRepo uriRepo;
  private final ModelMapper modelMapper;

  @Override
  public URIInput connect(URIInput uri) {
    URI createdItem = uriRepo.save(modelMapper.map(uri, URI.class));
    return modelMapper.map(createdItem, URIInput.class);
  }
}
