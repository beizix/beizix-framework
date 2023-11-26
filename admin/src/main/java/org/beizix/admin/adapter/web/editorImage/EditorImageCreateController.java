package org.beizix.admin.adapter.web.editorImage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.configuration.adapter.web.rest.RestResponse;
import org.beizix.core.configuration.application.enums.ContentDispositionType;
import org.beizix.core.configuration.application.enums.FileUploadType;
import org.beizix.core.usecase.file.upload.application.port.in.FileUploadPortIn;
import org.beizix.core.usecase.file.url.application.port.in.FileUrlPortIn;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class EditorImageCreateController {
  private final FileUploadPortIn fileUploadPortIn;
  private final FileUrlPortIn fileUrlPortIn;

  @PostMapping(path = "/api/editorImage/upload")
  ResponseEntity<?> editorImageUpload(MultipartFile editorImage) throws IOException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .message(
                    fileUrlPortIn.connect(
                        ContentDispositionType.INLINE,
                        fileUploadPortIn
                            .connect(FileUploadType.EDITOR_IMAGE, editorImage)
                            .orElse(null)))
                .build());
  }
}
