package org.beizix.core.application.port.in.uri;

import java.util.Optional;
import org.beizix.core.application.domain.uri.model.list.URIOutput;
import org.beizix.core.config.enums.AppType;

public interface URIViewPortIn {
  Optional<URIOutput> connect(AppType appType, String id);
}
