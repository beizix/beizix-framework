package org.beizix.core.application.port.in.uri;

import org.beizix.core.config.enums.AppType;

public interface URIRemovePortIn {
  void connect(AppType appType, String id);
}
