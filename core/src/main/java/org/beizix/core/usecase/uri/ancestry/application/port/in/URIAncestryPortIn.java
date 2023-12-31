package org.beizix.core.usecase.uri.ancestry.application.port.in;

import java.util.List;
import org.beizix.core.usecase.uri.ancestry.application.domain.URIAncestry;
import org.beizix.core.config.application.enums.AppType;

public interface URIAncestryPortIn {
  List<URIAncestry> connect(AppType appType, String uri);
}
