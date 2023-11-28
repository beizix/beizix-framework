package org.beizix.admin.usecase.uri.sort.application.port.in;

import org.beizix.admin.usecase.uri.sort.application.domain.URISortCommand;

import java.util.List;

public interface URISortPortIn {
  void connect(List<URISortCommand> uris);
}
