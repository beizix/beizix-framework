package org.beizix.core.adapter.persistence.uri;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.uri.repository.URIListRepo;
import org.beizix.core.application.domain.uri.model.list.URIViewOutput;
import org.beizix.core.application.port.out.uri.URIListPortOut;
import org.beizix.core.config.enums.AppType;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class URIListDao implements URIListPortOut {
  private final URIListRepo uriListRepo;

  @Override
  public List<URIViewOutput> connect(AppType appType) {
    return uriListRepo.findAllByAppType(appType).stream()
        .map(
            entity ->
                new URIViewOutput(
                    entity.getId(),
                    entity.getAppType(),
                    entity.getParentId(),
                    entity.getOrderNo(),
                    entity.getText(),
                    entity.getUri(),
                    entity.getShowOnNavi(),
                    entity.getOgTitle(),
                    entity.getOgDesc(),
                    entity.getOgKeywords(),
                    entity.getOgImage(),
                    entity.getRoles()))
        .collect(Collectors.toList());
  }
}
