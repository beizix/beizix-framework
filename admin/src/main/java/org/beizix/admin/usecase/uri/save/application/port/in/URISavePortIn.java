package org.beizix.admin.usecase.uri.save.application.port.in;

import java.io.IOException;
import org.beizix.core.application.domain.uri.model.save.URIInput;
import org.springframework.web.multipart.MultipartFile;

public interface URISavePortIn {
  String connect(URIInput uri, MultipartFile ogImageFile, boolean checkDuplicate)
      throws IOException;
}
