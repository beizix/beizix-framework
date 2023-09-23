package org.beizix.core.application.port.in.uri;

import java.io.IOException;
import org.beizix.core.application.domain.uri.model.URIInput;

public interface URISavePortIn {
  URIInput connect(URIInput uri, boolean checkDuplicate) throws IOException;
}
