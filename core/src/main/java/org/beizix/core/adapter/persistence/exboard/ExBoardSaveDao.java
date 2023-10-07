package org.beizix.core.adapter.persistence.exboard;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.core.adapter.persistence.common.model.FileUploadInfoEmbeddable;
import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.beizix.core.adapter.persistence.exboard.model.ExBoardAttachment;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.beizix.core.application.port.out.exboard.ExBoardSavePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ExBoardSaveDao implements ExBoardSavePortOut {
  private final ExBoardRepo exBoardRepo;

  @Override
  public Long connect(
      Long id,
      String title,
      String content,
      Boolean visible,
      LocalDateTime boardStartDate,
      LocalDateTime boardEndDate,
      FileUploadOutput representImage,
      String repImgAlt,
      List<FileUploadOutput> attachments,
      FileUploadOutput privateAttachment,
      Integer orderNo) {

    ExBoard entity =
        exBoardRepo.save(
            new ExBoard(
                id,
                title,
                content,
                visible,
                Optional.ofNullable(representImage)
                    .map(
                        repImg ->
                            new FileUploadInfoEmbeddable(
                                repImg.getType(),
                                repImg.getPath(),
                                repImg.getName(),
                                repImg.getOriginName(),
                                repImg.getFileLength()))
                    .orElse(null),
                repImgAlt,
                Optional.ofNullable(privateAttachment)
                    .map(
                        at ->
                            new FileUploadInfoEmbeddable(
                                at.getType(),
                                at.getPath(),
                                at.getName(),
                                at.getOriginName(),
                                at.getFileLength()))
                    .orElse(null),
                CollectionUtils.emptyIfNull(attachments).stream()
                    .map(
                        i ->
                            new ExBoardAttachment(
                                null,
                                new FileUploadInfoEmbeddable(
                                    i.getType(),
                                    i.getPath(),
                                    i.getName(),
                                    i.getOriginName(),
                                    i.getFileLength()),
                                null))
                    .collect(Collectors.toSet()),
                boardStartDate,
                boardEndDate,
                orderNo));

    return entity.getId();
  }
}
