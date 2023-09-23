package org.beizix.core.application.port.out.uri;

import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.domain.uri.model.URIInput;

import java.util.List;

public interface URIListPortOut {
  List<URIInput> connect(AppType appType);
}
