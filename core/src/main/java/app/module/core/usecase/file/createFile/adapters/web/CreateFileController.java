package app.module.core.usecase.file.createFile.adapters.web;

import app.module.core.config.application.enums.ContentDispositionType;
import app.module.core.usecase.file.createFile.adapters.web.model.CreateFileReqVO;
import app.module.core.usecase.file.createFile.adapters.web.model.CreateFileResVO;
import app.module.core.usecase.file.createFile.ports.CreateFilePortIn;
import app.module.core.usecase.file.createFile.ports.application.domain.CreateFile;
import app.module.core.usecase.file.createFile.ports.application.domain.CreateFileCmd;
import app.module.core.usecase.file.saveToStorage.ports.SaveToStoragePortIn;
import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import app.module.core.usecase.file.url.application.port.in.FileUrlPortIn;
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
  private final FileUrlPortIn fileUrlPortIn;

  @PostMapping(path = "/api/file/create")
  ResponseEntity<?> operate(
      @RequestPart(value = "reqVO") CreateFileReqVO reqVO,
      @RequestPart(value = "file") MultipartFile multipartFile)
      throws IOException {

    // storage 에 물리적 파일 저장. `LOCAL` or `S3`
    SaveToStorage saveToStorage =
        saveToStoragePortIn
            .operate(reqVO.getFileUploadType(), multipartFile)
            .orElseThrow(() -> new RuntimeException("물리적 파일 저장에 실패했습니다."));

    // DB 에 파일정보 저장
    CreateFile createFile =
        createFilePortIn
            .operate(
                new CreateFileCmd(
                    saveToStorage.getType(),
                    saveToStorage.getPath(),
                    saveToStorage.getName(),
                    saveToStorage.getOriginName(),
                    saveToStorage.getFileLength()))
            .orElseThrow(() -> new RuntimeException("업로드 파일정보 DB 저장에 실패했습니다."));

    // 참조 URL 정보 얻기. `LOCAL` or `S3`
    String referURL = fileUrlPortIn.connect(ContentDispositionType.INLINE, saveToStorage);

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            new CreateFileResVO(
                createFile.getId(),
                createFile.getType(),
                createFile.getPath(),
                createFile.getName(),
                createFile.getOriginName(),
                createFile.getFileLength(),
                referURL));
  }
}
