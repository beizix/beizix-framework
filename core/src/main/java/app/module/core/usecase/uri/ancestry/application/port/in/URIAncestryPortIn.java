package app.module.core.usecase.uri.ancestry.application.port.in;

import java.util.List;

import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.ancestry.application.domain.URIAncestry;

public interface URIAncestryPortIn {
  List<URIAncestry> connect(AppType appType, String uri);
}
