package org.beizix.core.application.port.in.uri;

import java.util.List;
import org.beizix.core.application.domain.uri.model.list.URIViewOutput;
import org.beizix.core.config.enums.AppType;

public interface URIMatchingParentsPortIn {
  List<URIViewOutput> connect(AppType appType, String uri);
}
