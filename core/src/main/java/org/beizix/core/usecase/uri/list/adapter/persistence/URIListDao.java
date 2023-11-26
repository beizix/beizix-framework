package org.beizix.core.usecase.uri.list.adapter.persistence;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.usecase.uri.list.application.port.out.URIListPortOut;
import org.beizix.core.usecase.uri.list.domain.URIElement;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class URIListDao implements URIListPortOut {

  private final URIListRepo uriListRepo;

  @Override
  public List<URIElement> connect(AppType appType) {
    return uriListRepo.findAllByAppType(appType).stream()
        .map(
            entity ->
                new URIElement(
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
