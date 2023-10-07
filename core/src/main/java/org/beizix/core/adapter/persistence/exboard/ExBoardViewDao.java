package org.beizix.core.adapter.persistence.exboard;

import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.beizix.core.application.domain.exboard.model.view.ExBoardViewAttachOutput;
import org.beizix.core.application.domain.exboard.model.view.ExBoardViewOutput;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.beizix.core.application.port.out.exboard.ExBoardViewPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ExBoardViewDao implements ExBoardViewPortOut<ExBoardViewOutput> {
  private final ExBoardRepo exBoardRepo;

  @Override
  public Optional<ExBoardViewOutput> connect(Long id) {
    Optional<ExBoard> result = exBoardRepo.findById(id);
    return result.map(
        item ->
            new ExBoardViewOutput(
                item.getCreatedBy(),
                item.getCreatedAt(),
                item.getUpdatedBy(),
                item.getUpdatedAt(),
                item.getId(),
                item.getTitle(),
                item.getContent(),
                item.getVisible(),
                item.getBoardStartDate(),
                item.getBoardEndDate(),
                Optional.ofNullable(item.getRepresentImage())
                    .map(
                        repImg ->
                            new FileUploadOutput(
                                repImg.getType(),
                                repImg.getPath(),
                                repImg.getName(),
                                repImg.getName(),
                                repImg.getFileLength()))
                    .orElse(null),
                item.getRepImgAlt(),
                CollectionUtils.emptyIfNull(item.getAttachments()).stream()
                    .map(
                        a ->
                            new ExBoardViewAttachOutput(
                                a.getCreatedBy(),
                                a.getCreatedAt(),
                                a.getUpdatedBy(),
                                a.getUpdatedAt(),
                                a.getId(),
                                new FileUploadOutput(
                                    a.getFileUploadOutput().getType(),
                                    a.getFileUploadOutput().getPath(),
                                    a.getFileUploadOutput().getName(),
                                    a.getFileUploadOutput().getOriginName(),
                                    a.getFileUploadOutput().getFileLength())))
                    .collect(Collectors.toList()),
                Optional.ofNullable(item.getPrivateAttachment())
                    .map(
                        at ->
                            new FileUploadOutput(
                                at.getType(),
                                at.getPath(),
                                at.getName(),
                                at.getOriginName(),
                                at.getFileLength()))
                    .orElse(null),
                item.getOrderNo()));
  }
}
