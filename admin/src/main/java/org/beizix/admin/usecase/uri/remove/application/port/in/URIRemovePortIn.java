package org.beizix.admin.usecase.uri.remove.application.port.in;

import org.beizix.core.configuration.application.enums.AppType;

public interface URIRemovePortIn {
  void connect(AppType appType, String id);
}
