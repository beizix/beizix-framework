package org.beizix.core.usecase.uri.currentmatch.application.port.in;

import org.beizix.core.usecase.uri.currentmatch.domain.URICurrentMatching;
import org.beizix.core.config.enums.AppType;

public interface URICurrentMatchingPortIn {
  String PATH_VAR = "{{pathVar}}";

  URICurrentMatching connect(AppType appType, String uri);
}
