package org.beizix.core.application.domain.exboard;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveAttachInput;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.port.in.exboard.ExBoardSavePortIn;
import org.beizix.core.application.port.in.fileupload.FileUploadPortIn;
import org.beizix.core.application.port.out.exboard.ExBoardAttachmentRemovePortOut;
import org.beizix.core.application.port.out.exboard.ExBoardNextOrderNoPortOut;
import org.beizix.core.application.port.out.exboard.ExBoardSavePortOut;
import org.beizix.core.config.enums.FileUploadType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
class ExBoardSaveService implements ExBoardSavePortIn {
  private final ExBoardSavePortOut exBoardSavePortOut;
  private final FileUploadPortIn fileUploadPortIn;
  private final ExBoardAttachmentRemovePortOut exBoardAttachmentRemovePortOut;
  private final ExBoardNextOrderNoPortOut exBoardNextOrderNoPortOut;

  @Override
  @Transactional
  public Long connect(
      ExBoardSaveInput saveInput,
      MultipartFile representImgFile,
      MultipartFile privateAttachment,
      List<MultipartFile> publicAttachments)
      throws IOException {
    // 정렬 순서 지정하기
    saveInput.setOrderNo(
        Optional.ofNullable(saveInput.getOrderNo()).orElse(exBoardNextOrderNoPortOut.connect()));

    // 대표 이미지 파일 저장
    fileUploadPortIn
        .connect(FileUploadType.EXAMPLE_REP, representImgFile)
        .ifPresent(saveInput::setRepresentImage);

    // 외부 비공개 파일 저장
    fileUploadPortIn
        .connect(FileUploadType.EXAMPLE_PRIVATE, privateAttachment)
        .ifPresent(saveInput::setPrivateAttachment);

    // 삭제해야 할 `다건 첨부 파일` 정보가 있다면 삭제
    if (CollectionUtils.isNotEmpty(saveInput.getRemoveAttachmentIds()))
      saveInput.getRemoveAttachmentIds().forEach(exBoardAttachmentRemovePortOut::connect);

    // `다건 첨부 파일` 저장
    if (CollectionUtils.isNotEmpty(publicAttachments)) {
      saveInput.setAttachments(
          publicAttachments.stream()
              .filter(multipartFile -> !multipartFile.isEmpty())
              .map(
                  at -> {
                    try {
                      return new ExBoardSaveAttachInput(
                          fileUploadPortIn.connect(FileUploadType.EXAMPLE_PUBLIC, at).orElse(null),
                          saveInput);
                    } catch (IOException e) {
                      throw new RuntimeException(e);
                    }
                  })
              .collect(Collectors.toList()));
    }

    // create/update 수행
    return exBoardSavePortOut.connect(saveInput);
  }
}
