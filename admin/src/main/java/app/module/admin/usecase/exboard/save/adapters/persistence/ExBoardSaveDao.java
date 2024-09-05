package app.module.admin.usecase.exboard.save.adapters.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import app.module.admin.usecase.exboard.save.ports.ExBoardSavePortOut;
import app.module.core.config.adapter.persistence.component.FileUploadInfoEmbeddable;
import app.module.core.config.adapter.persistence.entity.ExBoard;
import app.module.core.config.adapter.persistence.entity.ExBoardAttachment;
import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ExBoardSaveDao implements ExBoardSavePortOut {
  private final ExBoardSaveRepo exBoardRepo;

  @Override
  public Long connect(
      Long id,
      String title,
      String content,
      Boolean visible,
      LocalDateTime boardStartDate,
      LocalDateTime boardEndDate,
      SaveToStorage representImage,
      String repImgAlt,
      List<SaveToStorage> attachments,
      SaveToStorage privateAttachment,
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
