package app.module.admin.usecase.uri.sort.application.port.out;

import app.module.admin.usecase.uri.sort.application.domain.URISortCommand;

import java.util.List;

public interface URISortPortOut {
  void connect(List<URISortCommand> uris);
}
