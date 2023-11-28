package org.beizix.admin.usecase.uri.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uri.save.application.port.out.URISavePortOut;
import org.beizix.core.config.adapter.persistence.entity.URI;
import org.beizix.admin.usecase.uri.save.application.domain.URISaveCommand;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class URISaveDao implements URISavePortOut {
  private final URISaveRepo uriRepo;
  private final ModelMapper modelMapper;

  @Override
  public String connect(URISaveCommand uri) {
    URI createdItem = uriRepo.save(modelMapper.map(uri, URI.class));
    return createdItem.getId();
  }
}
