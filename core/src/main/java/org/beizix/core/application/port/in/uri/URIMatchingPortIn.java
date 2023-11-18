package org.beizix.core.application.port.in.uri;

import org.beizix.core.application.domain.uri.model.list.URIViewOutput;
import org.beizix.core.config.enums.AppType;

public interface URIMatchingPortIn {
  String PATH_VAR = "{{pathVar}}";

  URIViewOutput connect(AppType appType, String uri);
}
