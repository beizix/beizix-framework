package org.beizix.core.application.port.in.uri;

import java.util.List;
import org.beizix.core.application.domain.uri.model.list.URIOutput;
import org.beizix.core.config.enums.AppType;

public interface URIListPortIn {
  List<URIOutput> connect(AppType appType);
}
