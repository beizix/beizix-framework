package org.beizix.core.application.domain.exboard;

import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.exboard.model.ExBoardAttachment;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.port.in.exboard.ExBoardSavePortIn;
import org.beizix.core.application.port.in.fileupload.FileUploadPortIn;
import org.beizix.core.application.port.out.exboard.ExBoardAttachmentRemovePortOut;
import org.beizix.core.application.port.out.exboard.ExBoardAttachmentSavePortOut;
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
  private final ExBoardAttachmentSavePortOut exBoardAttachmentSavePortOut;
  private final ExBoardAttachmentRemovePortOut exBoardAttachmentRemovePortOut;
  private final ExBoardNextOrderNoPortOut exBoardNextOrderNoPortOut;

  @Override
  @Transactional
  public ExBoardSaveInput connect(ExBoardSaveInput exBoard) throws IOException {
    // 정렬 순서 지정하기
    exBoard.setOrderNo(
        Optional.ofNullable(exBoard.getOrderNo()).orElse(exBoardNextOrderNoPortOut.connect()));

    // 외부 공개 파일 저장
    fileUploadPortIn
        .connect(FileUploadType.EXAMPLE_REP, exBoard.getRepresentImgFile())
        .ifPresent(exBoard::setRepresentImage);

    // 외부 비공개 파일 저장
    fileUploadPortIn
        .connect(FileUploadType.EXAMPLE_PRIVATE, exBoard.getMultipartPrivateAttachment())
        .ifPresent(exBoard::setPrivateAttachment);

    // 삭제해야 할 다건 첨부 파일 정보가 있다면 삭제
    exBoard.getRemoveAttachmentIds().forEach(exBoardAttachmentRemovePortOut::connect);

    // create/update 수행
    ExBoardSaveInput operateItem = exBoardSavePortOut.connect(exBoard);

    // 다건 첨부파일 저장
    for (MultipartFile attachment : exBoard.getMultipartAttachments()) {
      exBoardAttachmentSavePortOut.connect(
          ExBoardAttachment.builder()
              .exBoard(operateItem)
              .fileUploadInfo(
                  fileUploadPortIn.connect(FileUploadType.EXAMPLE_PUBLIC, attachment).orElse(null))
              .build());
    }

    return operateItem;
  }
}
