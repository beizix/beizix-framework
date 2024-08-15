package app.module.core.usecase.uri.view.application.port.in;

import java.util.Optional;

import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.view.application.domain.URIView;

public interface URIViewPortIn {

  Optional<URIView> connect(AppType appType, String id);
}
