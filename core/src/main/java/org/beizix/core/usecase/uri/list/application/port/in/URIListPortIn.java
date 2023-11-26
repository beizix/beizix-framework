package org.beizix.core.usecase.uri.list.application.port.in;

import java.util.List;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.usecase.uri.list.domain.URIElement;

public interface URIListPortIn {
  List<URIElement> connect(AppType appType);
}
