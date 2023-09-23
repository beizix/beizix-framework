package org.beizix.core.application.port.in.uri;

import org.beizix.core.application.domain.uri.model.URISortInput;

import java.util.List;

public interface URISortPortIn {
  void connect(List<URISortInput> uris);
}
