package org.beizix.core.feature.uri.application.service;

import org.beizix.core.config.enums.AppType;

public interface URIRemoveService {
  void operate(AppType appType, String id);
}
