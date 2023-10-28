package org.beizix.core.application.port.in.uri;

import org.beizix.core.application.domain.uri.model.list.URIOutput;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.domain.uri.model.URIInput;

import java.util.Optional;

public interface URIViewPortIn {
  Optional<URIOutput> connect(AppType appType, String id);
}
