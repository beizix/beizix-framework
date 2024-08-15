package app.module.core.usecase.uri.currentmatch.application.port.in;

import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.currentmatch.application.domain.URICurrentMatching;

public interface URICurrentMatchingPortIn {
  String PATH_VAR = "{{pathVar}}";

  URICurrentMatching connect(AppType appType, String uri);
}
