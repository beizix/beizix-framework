package org.beizix.core.usecase.uri.save.application.port.in;

import java.io.IOException;
import org.beizix.core.usecase.uri.save.application.domain.URISaveCommand;
import org.springframework.web.multipart.MultipartFile;

public interface URISavePortIn {
  String connect(URISaveCommand uri, MultipartFile ogImageFile, boolean checkDuplicate)
      throws IOException;
}
