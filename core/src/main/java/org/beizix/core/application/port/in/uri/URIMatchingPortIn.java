package org.beizix.core.application.port.in.uri;

import org.beizix.core.application.domain.uri.model.list.URIOutput;
import org.beizix.core.config.enums.AppType;

public interface URIMatchingPortIn {
  String pathVariable = "{{pathVar}}";

  URIOutput connect(AppType appType, String uri);
}
