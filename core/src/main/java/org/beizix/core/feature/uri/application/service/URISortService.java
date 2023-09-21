package org.beizix.core.feature.uri.application.service;

import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.model.URISort;

import java.util.List;

public interface URISortService {
  void operate(List<URISort> uris);
}
