package org.beizix.core.application.port.in.uri;

import org.beizix.core.application.domain.uri.model.toptier.URIOutput;
import org.beizix.core.config.enums.AppType;

public interface URITopTierPortIn {
  URIOutput connect(AppType appType);
}
