package org.beizix.core.adapter.persistence.uri;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.uri.model.URI;
import org.beizix.core.adapter.persistence.uri.repository.URIRepo;
import org.beizix.core.application.domain.uri.model.save.URIInput;
import org.beizix.core.application.port.out.uri.URISavePortOut;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class URISaveDao implements URISavePortOut {
  private final URIRepo uriRepo;
  private final ModelMapper modelMapper;

  @Override
  public String connect(URIInput uri) {
    URI createdItem = uriRepo.save(modelMapper.map(uri, URI.class));
    return createdItem.getId();
  }
}
