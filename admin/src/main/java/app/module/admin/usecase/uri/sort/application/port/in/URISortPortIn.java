package app.module.admin.usecase.uri.sort.application.port.in;

import app.module.admin.usecase.uri.sort.application.domain.URISortCommand;

import java.util.List;

public interface URISortPortIn {
  void connect(List<URISortCommand> uris);
}
