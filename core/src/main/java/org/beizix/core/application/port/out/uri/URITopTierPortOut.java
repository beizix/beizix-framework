package org.beizix.core.application.port.out.uri;

import org.beizix.core.application.domain.uri.model.toptier.URIOutput;
import org.beizix.core.config.enums.AppType;

public interface URITopTierPortOut {
  URIOutput connect(AppType appType);
}
