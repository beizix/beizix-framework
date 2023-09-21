package org.beizix.core.feature.uri.application.service;

import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;

import java.util.Optional;

public interface URIViewService {
  Optional<URI> operate(AppType appType, String id);
}
