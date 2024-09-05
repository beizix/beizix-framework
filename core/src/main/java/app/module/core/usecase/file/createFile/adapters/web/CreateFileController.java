package app.module.core.usecase.file.createFile.adapters.web;

import app.module.core.usecase.file.createFile.adapters.web.model.CreateFileReqVO;
import app.module.core.usecase.file.createFile.ports.CreateFilePortIn;
import app.module.core.usecase.file.createFile.ports.application.domain.CreateFile;
import app.module.core.usecase.file.createFile.ports.application.domain.CreateFileCmd;
import app.module.core.usecase.file.saveToStorage.ports.SaveToStoragePortIn;
import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
class CreateFileController {
  private final SaveToStoragePortIn saveToStoragePortIn;
  private final CreateFilePortIn createFilePortIn;

  @PostMapping(path = "/api/file/create")
  ResponseEntity<?> operate(
      @RequestPart(value = "reqVO") CreateFileReqVO reqVO,
      @RequestPart(value = "file") MultipartFile multipartFile)
      throws IOException {

    SaveToStorage saveToStorage =
        saveToStoragePortIn.operate(reqVO.getFileUploadType(), multipartFile).orElseThrow();

    CreateFile createFile = createFilePortIn.operate(
        new CreateFileCmd(
            saveToStorage.getType(),
            saveToStorage.getPath(),
            saveToStorage.getName(),
            saveToStorage.getOriginName(),
            saveToStorage.getFileLength())).orElseThrow();

    return ResponseEntity.status(HttpStatus.OK).body(createFile);
  }
}
