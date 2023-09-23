package org.beizix.core.application.port.out.uri;

import org.beizix.core.application.domain.uri.model.URISortInput;

import java.util.List;

public interface URISortPortOut {
  void connect(List<URISortInput> uris);
}
