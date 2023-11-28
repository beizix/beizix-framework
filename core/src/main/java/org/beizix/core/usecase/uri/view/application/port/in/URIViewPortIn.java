package org.beizix.core.usecase.uri.view.application.port.in;

import java.util.Optional;
import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.usecase.uri.view.application.domain.URIView;

public interface URIViewPortIn {

  Optional<URIView> connect(AppType appType, String id);
}
