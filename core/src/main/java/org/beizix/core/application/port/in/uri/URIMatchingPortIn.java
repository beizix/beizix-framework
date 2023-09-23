package org.beizix.core.application.port.in.uri;

import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.domain.uri.model.URIInput;

public interface URIMatchingPortIn {
  String pathVariable = "{{pathVars}}";
  URIInput connect(AppType appType, String uri);
}
