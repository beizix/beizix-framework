package app.module.core.usecase.uri.view.application.port.in;

import java.util.Optional;

import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.list.application.port.in.URIListPortIn;
import app.module.core.usecase.uri.view.application.domain.URIView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class URIViewService implements URIViewPortIn {

  private final URIListPortIn uriListPortIn;

  @Override
  public Optional<URIView> connect(AppType appType, String id) {
    return uriListPortIn.connect(appType).stream()
        .filter(uriDetail -> id.equals(uriDetail.getId()))
        .map(uriDetail -> new URIView(uriDetail.getId(), uriDetail.getAppType(),
            uriDetail.getParentId(), uriDetail.getOrderNo(), uriDetail.getText(),
            uriDetail.getUri(), uriDetail.getShowOnNavi(), uriDetail.getOgTitle(),
            uriDetail.getOgDesc(), uriDetail.getOgKeywords(), uriDetail.getOgImage(),
            uriDetail.getRoles()))
        .findFirst();
  }
}
