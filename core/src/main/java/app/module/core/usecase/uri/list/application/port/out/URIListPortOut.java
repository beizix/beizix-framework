package app.module.core.usecase.uri.list.application.port.out;

import java.util.List;

import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.list.application.domain.URIElement;

public interface URIListPortOut {

  List<URIElement> connect(AppType appType);
}
