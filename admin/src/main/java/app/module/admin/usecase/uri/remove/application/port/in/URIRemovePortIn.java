package app.module.admin.usecase.uri.remove.application.port.in;

import app.module.core.config.application.enums.AppType;

public interface URIRemovePortIn {
  void connect(AppType appType, String id);
}
