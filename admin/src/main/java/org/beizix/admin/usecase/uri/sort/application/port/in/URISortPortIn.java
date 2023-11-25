package org.beizix.admin.usecase.uri.sort.application.port.in;

import org.beizix.core.application.domain.uri.model.URISortInput;

import java.util.List;

public interface URISortPortIn {
  void connect(List<URISortInput> uris);
}
