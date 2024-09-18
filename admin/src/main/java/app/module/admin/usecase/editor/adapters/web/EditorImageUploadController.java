package app.module.admin.usecase.editor.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import app.module.core.config.adapter.web.rest.response.RestResponse;
import app.module.core.config.application.enums.ContentDispositionType;
import app.module.core.config.application.enums.FileUploadType;
import app.module.core.usecase.file.saveToStorage.ports.SaveToStoragePortIn;
import app.module.core.usecase.file.url.application.port.in.FileUrlPortIn;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class EditorImageUploadController {
  private final SaveToStoragePortIn saveToStoragePortIn;
  private final FileUrlPortIn fileUrlPortIn;

  @PostMapping(path = "/api/editorImage/upload")
  ResponseEntity<?> editorImageUpload(MultipartFile editorImage) throws IOException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .message(
                    fileUrlPortIn.getInline(
                        saveToStoragePortIn
                            .operate(FileUploadType.EDITOR_IMAGE, editorImage)
                            .orElse(null)))
                .build());
  }
}
