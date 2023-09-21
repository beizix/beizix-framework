package org.beizix.core.feature.uri.application.service;

import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;

public interface URIHierarchyService {
  URI operate(AppType appType);
}
