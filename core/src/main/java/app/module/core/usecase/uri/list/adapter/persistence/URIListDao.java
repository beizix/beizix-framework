package app.module.core.usecase.uri.list.adapter.persistence;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.list.application.port.out.URIListPortOut;
import app.module.core.usecase.uri.list.application.domain.URIElement;
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
