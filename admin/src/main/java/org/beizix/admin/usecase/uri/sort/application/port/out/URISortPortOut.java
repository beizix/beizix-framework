package org.beizix.admin.usecase.uri.sort.application.port.out;

import org.beizix.core.application.domain.uri.model.URISortInput;

import java.util.List;

public interface URISortPortOut {
  void connect(List<URISortInput> uris);
}
