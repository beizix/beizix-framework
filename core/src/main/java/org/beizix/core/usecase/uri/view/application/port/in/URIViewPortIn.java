package org.beizix.core.usecase.uri.view.application.port.in;

import java.util.Optional;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.usecase.uri.view.domain.URIView;

public interface URIViewPortIn {

  Optional<URIView> connect(AppType appType, String id);
}
