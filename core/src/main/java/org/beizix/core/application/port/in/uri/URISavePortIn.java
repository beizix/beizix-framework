package org.beizix.core.application.port.in.uri;

import java.io.IOException;
import org.beizix.core.application.domain.uri.model.save.URIInput;
import org.springframework.web.multipart.MultipartFile;

public interface URISavePortIn {
  String connect(URIInput uri, MultipartFile ogImageFile, boolean checkDuplicate)
      throws IOException;
}
