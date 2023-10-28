package org.beizix.core.adapter.persistence.uri;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.uri.repository.URIListRepo;
import org.beizix.core.application.domain.uri.model.list.URIOutput;
import org.beizix.core.application.port.out.uri.URIListPortOut;
import org.beizix.core.config.enums.AppType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class URIListDao implements URIListPortOut {
  private final URIListRepo uriListRepo;
  private final ModelMapper modelMapper;

  @Override
  public List<URIOutput> connect(AppType appType) {
    return uriListRepo.findAllByAppType(appType).stream()
        .map(uriEntity -> modelMapper.map(uriEntity, URIOutput.class))
        .collect(Collectors.toList());
  }
}
