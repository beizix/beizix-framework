package app.module.core.usecase.uri.list.application.port.in;

import java.util.List;
import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.list.application.domain.URIElement;

public interface URIListPortIn {
  List<URIElement> connect(AppType appType);
}
