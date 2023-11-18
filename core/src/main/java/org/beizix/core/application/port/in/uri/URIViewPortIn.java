package org.beizix.core.application.port.in.uri;

import java.util.Optional;
import org.beizix.core.application.domain.uri.model.list.URIViewOutput;
import org.beizix.core.config.enums.AppType;

public interface URIViewPortIn {
  Optional<URIViewOutput> connect(AppType appType, String id);
}
