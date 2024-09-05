package app.module.core.usecase.exboard.view.adapter.persistence;

import java.util.Optional;
import java.util.stream.Collectors;

import app.module.core.usecase.exboard.view.application.domain.ExBoardView;
import app.module.core.usecase.exboard.view.application.domain.ExBoardViewAttach;
import app.module.core.usecase.exboard.view.application.port.out.ExBoardViewPortOut;
import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import app.module.core.config.adapter.persistence.entity.ExBoard;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ExBoardViewDao implements ExBoardViewPortOut<ExBoardView> {
  private final ExBoardViewRepo exBoardRepo;

  @Override
  public Optional<ExBoardView> connect(Long id) {
    Optional<ExBoard> result = exBoardRepo.findById(id);
    return result.map(
        item ->
            new ExBoardView(
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
                            new SaveToStorage(
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
                            new ExBoardViewAttach(
                                a.getCreatedBy(),
                                a.getCreatedAt(),
                                a.getUpdatedBy(),
                                a.getUpdatedAt(),
                                a.getId(),
                                new SaveToStorage(
                                    a.getFileUploadOutput().getType(),
                                    a.getFileUploadOutput().getPath(),
                                    a.getFileUploadOutput().getName(),
                                    a.getFileUploadOutput().getOriginName(),
                                    a.getFileUploadOutput().getFileLength())))
                    .collect(Collectors.toList()),
                Optional.ofNullable(item.getPrivateAttachment())
                    .map(
                        at ->
                            new SaveToStorage(
                                at.getType(),
                                at.getPath(),
                                at.getName(),
                                at.getOriginName(),
                                at.getFileLength()))
                    .orElse(null),
                item.getOrderNo()));
  }
}
