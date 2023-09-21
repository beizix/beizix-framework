package org.beizix.core.feature.uri.application.service;

import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;

import java.util.List;

public interface URIMatchingParentsService {
  List<URI> operate(AppType appType, String uri);
}
