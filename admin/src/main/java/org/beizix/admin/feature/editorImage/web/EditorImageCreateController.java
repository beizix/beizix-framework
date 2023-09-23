package org.beizix.admin.feature.editorImage.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.common.rest.RestResponseDto;
import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileUploadType;
import org.beizix.core.application.port.in.fileupload.FileUploadPortIn;
import org.beizix.core.feature.fileUrl.application.service.FileUrlService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class EditorImageCreateController {
  private final FileUploadPortIn fileUploadPortIn;
  private final FileUrlService fileUrlService;

  @PostMapping(path = "/api/editorImage/upload")
  ResponseEntity<?> editorImageUpload(MultipartFile editorImage) throws IOException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponseDto.builder()
                .message(
                    fileUrlService.operate(
                        ContentDispositionType.INLINE,
                        fileUploadPortIn
                            .connect(FileUploadType.EDITOR_IMAGE, editorImage)
                            .orElse(null)))
                .build());
  }
}
