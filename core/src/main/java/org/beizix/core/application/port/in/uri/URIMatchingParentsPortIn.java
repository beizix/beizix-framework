package org.beizix.core.application.port.in.uri;

import java.util.List;
import org.beizix.core.application.domain.uri.model.matchparent.URIMatchParentVO;
import org.beizix.core.config.enums.AppType;

public interface URIMatchingParentsPortIn {
  List<URIMatchParentVO> connect(AppType appType, String uri);
}
