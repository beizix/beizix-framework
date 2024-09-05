package app.module.admin.usecase.exboard.save.ports.application;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import app.module.admin.usecase.exboard.save.ports.ExBoardSavePortIn;
import app.module.core.usecase.exboard.view.application.domain.ExBoardView;
import app.module.core.usecase.exboard.view.application.port.in.ExBoardViewPortIn;
import app.module.admin.usecase.exboard.save.ports.ExBoardAttachmentRemovePortOut;
import app.module.admin.usecase.exboard.save.ports.ExBoardNextOrderNoPortOut;
import app.module.admin.usecase.exboard.save.ports.ExBoardSavePortOut;
import app.module.core.config.application.enums.FileUploadType;
import app.module.core.usecase.file.saveToStorage.ports.SaveToStoragePortIn;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
class ExBoardSaveService implements ExBoardSavePortIn {
  private final ExBoardSavePortOut exBoardSavePortOut;
  private final SaveToStoragePortIn saveToStoragePortIn;
  private final ExBoardAttachmentRemovePortOut exBoardAttachmentRemovePortOut;
  private final ExBoardNextOrderNoPortOut exBoardNextOrderNoPortOut;
  private final ExBoardViewPortIn<ExBoardView> exBoardViewPortIn;

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

    // 수정일 경우, 대표 이미지와 비공개 첨부 정보는 전달받지 않기에 기존 저장된 정보를 조회해서 가져온다.
    Optional<ExBoardView> viewOpt =
        id != null ? Optional.ofNullable(exBoardViewPortIn.connect(id)) : Optional.empty();

    // 정렬 순서 지정하기
    Integer orderNum = Optional.ofNullable(orderNo).orElse(exBoardNextOrderNoPortOut.connect());

    // 대표 이미지 파일 저장
    SaveToStorage repImg =
        representImgFile.isEmpty()
            ? viewOpt.map(ExBoardView::getRepresentImage).orElse(null)
            : saveToStoragePortIn.operate(FileUploadType.EXAMPLE_REP, representImgFile).orElse(null);

    // 외부 비공개 파일 저장
    SaveToStorage privateFile =
        privateAttachment.isEmpty()
            ? viewOpt.map(ExBoardView::getPrivateAttachment).orElse(null)
            : saveToStoragePortIn
                .operate(FileUploadType.EXAMPLE_PRIVATE, privateAttachment)
                .orElse(null);

    // 삭제해야 할 `다건 첨부 파일` 정보가 있다면 삭제
    CollectionUtils.emptyIfNull(removeAttachmentIds)
        .forEach(exBoardAttachmentRemovePortOut::connect);

    // `다건 첨부 파일` 저장
    List<SaveToStorage> attachInputs =
        CollectionUtils.emptyIfNull(publicAttachments).stream()
            .filter(multipartFile -> !multipartFile.isEmpty())
            .map(
                at -> {
                  try {
                    return saveToStoragePortIn.operate(FileUploadType.EXAMPLE_PUBLIC, at).orElse(null);
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
