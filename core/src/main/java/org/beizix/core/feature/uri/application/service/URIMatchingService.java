package org.beizix.core.feature.uri.application.service;

import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;

public interface URIMatchingService {
  String pathVariable = "{{pathVars}}";
  URI operate(AppType appType, String uri);
}
