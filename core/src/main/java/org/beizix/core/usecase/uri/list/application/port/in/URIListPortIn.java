package org.beizix.core.usecase.uri.list.application.port.in;

import java.util.List;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.usecase.uri.list.domain.URIDetail;

public interface URIListPortIn {
  List<URIDetail> connect(AppType appType);
}
