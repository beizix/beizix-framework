package org.beizix.core.application.domain.exboard;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
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
      Long id,
      String title,
      String content,
      Boolean visible,
      LocalDateTime boardStartDate,
      LocalDateTime boardEndDate,
      String repImgAlt,
      Integer orderNo,
      List<Long> removeAttachmentIds,
      MultipartFile representImgFile,
      MultipartFile privateAttachment,
      List<MultipartFile> publicAttachments)
      throws IOException {

    // 정렬 순서 지정하기
    Integer orderNum = Optional.ofNullable(orderNo).orElse(exBoardNextOrderNoPortOut.connect());

    // 대표 이미지 파일 저장
    FileUploadOutput repImg =
        fileUploadPortIn.connect(FileUploadType.EXAMPLE_REP, representImgFile).orElse(null);

    // 외부 비공개 파일 저장
    FileUploadOutput privateFile =
        fileUploadPortIn.connect(FileUploadType.EXAMPLE_PRIVATE, privateAttachment).orElse(null);

    // 삭제해야 할 `다건 첨부 파일` 정보가 있다면 삭제
    CollectionUtils.emptyIfNull(removeAttachmentIds)
        .forEach(exBoardAttachmentRemovePortOut::connect);

    // `다건 첨부 파일` 저장
    List<FileUploadOutput> attachInputs =
        CollectionUtils.emptyIfNull(publicAttachments).stream()
            .filter(multipartFile -> !multipartFile.isEmpty())
            .map(
                at -> {
                  try {
                    return fileUploadPortIn.connect(FileUploadType.EXAMPLE_PUBLIC, at).orElse(null);
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                })
            .collect(Collectors.toList());

    // create/update 수행
    return exBoardSavePortOut.connect(
        id,
        title,
        content,
        visible,
        boardStartDate,
        boardEndDate,
        repImg,
        repImgAlt,
        attachInputs,
        privateFile,
        orderNum);
  }
}
