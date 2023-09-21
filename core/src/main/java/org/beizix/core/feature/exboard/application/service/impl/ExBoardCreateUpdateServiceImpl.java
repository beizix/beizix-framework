package org.beizix.core.feature.exboard.application.service.impl;

import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.config.enums.FileUploadType;
import org.beizix.core.feature.exboard.application.model.ExBoard;
import org.beizix.core.feature.exboard.application.model.ExBoardAttachment;
import org.beizix.core.feature.exboard.application.service.ExBoardCreateUpdateService;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardAttachmentCreateDao;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardAttachmentRemoveDao;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardCreateUpdateDao;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardNextOrderNoDao;
import org.beizix.core.feature.fileUpload.application.service.FileUploadService;

@Service
@RequiredArgsConstructor
class ExBoardCreateUpdateServiceImpl implements ExBoardCreateUpdateService {
  private final ExBoardCreateUpdateDao exBoardCreateUpdateDao;
  private final FileUploadService fileUploadService;
  private final ExBoardAttachmentCreateDao exBoardAttachmentCreateDao;
  private final ExBoardAttachmentRemoveDao exBoardAttachmentRemoveDao;
  private final ExBoardNextOrderNoDao exBoardNextOrderNoDao;

  @Override
  @Transactional
  public ExBoard operate(ExBoard exBoard) throws IOException {
    // 정렬 순서 지정하기
    exBoard.setOrderNo(
        Optional.ofNullable(exBoard.getOrderNo()).orElse(exBoardNextOrderNoDao.operate()));

    // 외부 공개 파일 저장
    fileUploadService
        .operate(FileUploadType.EXAMPLE_REP, exBoard.getRepresentImgFile())
        .ifPresent(exBoard::setRepresentImage);

    // 외부 비공개 파일 저장
    fileUploadService
        .operate(FileUploadType.EXAMPLE_PRIVATE, exBoard.getMultipartPrivateAttachment())
        .ifPresent(exBoard::setPrivateAttachment);

    // 삭제해야 할 다건 첨부 파일 정보가 있다면 삭제
    exBoard.getRemoveAttachmentIds().forEach(exBoardAttachmentRemoveDao::operate);

    // create/update 수행
    ExBoard operateItem = exBoardCreateUpdateDao.operate(exBoard);

    // 다건 첨부파일 저장
    for (MultipartFile attachment : exBoard.getMultipartAttachments()) {
      exBoardAttachmentCreateDao.operate(
          ExBoardAttachment.builder()
              .exBoard(operateItem)
              .fileUploadInfo(
                  fileUploadService.operate(FileUploadType.EXAMPLE_PUBLIC, attachment).orElse(null))
              .build());
    }

    return operateItem;
  }
}
