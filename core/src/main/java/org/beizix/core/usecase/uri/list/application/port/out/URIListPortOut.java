package org.beizix.core.usecase.uri.list.application.port.out;

import java.util.List;
import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.usecase.uri.list.application.domain.URIElement;

public interface URIListPortOut {

  List<URIElement> connect(AppType appType);
}
