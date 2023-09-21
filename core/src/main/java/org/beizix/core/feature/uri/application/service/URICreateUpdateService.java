package org.beizix.core.feature.uri.application.service;

import java.io.IOException;
import org.beizix.core.feature.uri.application.model.URI;

public interface URICreateUpdateService {
  URI operate(URI uri, boolean checkDuplicate) throws IOException;
}
