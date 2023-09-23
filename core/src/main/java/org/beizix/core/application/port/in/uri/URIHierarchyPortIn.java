package org.beizix.core.application.port.in.uri;

import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.domain.uri.model.URIInput;

public interface URIHierarchyPortIn {
  URIInput connect(AppType appType);
}
