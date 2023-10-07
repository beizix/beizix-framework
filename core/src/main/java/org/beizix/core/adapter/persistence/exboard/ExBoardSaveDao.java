package org.beizix.core.adapter.persistence.exboard;

import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.core.adapter.persistence.common.model.FileUploadInfoEmbeddable;
import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.beizix.core.adapter.persistence.exboard.model.ExBoardAttachment;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.port.out.exboard.ExBoardSavePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ExBoardSaveDao implements ExBoardSavePortOut {
  private final ExBoardRepo exBoardRepo;

  @Override
  public Long connect(ExBoardSaveInput input) {
    ExBoard entity =
        exBoardRepo.save(
            new ExBoard(
                input.getId(),
                input.getTitle(),
                input.getContent(),
                input.getVisible(),
                Optional.ofNullable(input.getRepresentImage())
                    .map(
                        repImg ->
                            new FileUploadInfoEmbeddable(
                                repImg.getType(),
                                repImg.getPath(),
                                repImg.getName(),
                                repImg.getOriginName(),
                                repImg.getFileLength()))
                    .orElse(null),
                input.getRepImgAlt(),
                Optional.ofNullable(input.getPrivateAttachment())
                    .map(
                        at ->
                            new FileUploadInfoEmbeddable(
                                at.getType(),
                                at.getPath(),
                                at.getName(),
                                at.getOriginName(),
                                at.getFileLength()))
                    .orElse(null),
                CollectionUtils.emptyIfNull(input.getAttachments()).stream()
                    .map(
                        i ->
                            new ExBoardAttachment(
                                null,
                                new FileUploadInfoEmbeddable(
                                    i.getFileUploadOutput().getType(),
                                    i.getFileUploadOutput().getPath(),
                                    i.getFileUploadOutput().getName(),
                                    i.getFileUploadOutput().getOriginName(),
                                    i.getFileUploadOutput().getFileLength()),
                                null))
                    .collect(Collectors.toSet()),
                input.getBoardStartDate(),
                input.getBoardEndDate(),
                input.getOrderNo()));

    return entity.getId();
  }
}
