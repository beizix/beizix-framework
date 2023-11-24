package org.beizix.core.usecase.uri.list.application.port.out;

import java.util.List;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.usecase.uri.list.domain.URIDetail;

public interface URIListPortOut {

  List<URIDetail> connect(AppType appType);
}
