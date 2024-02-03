package org.beizix.core.usecase.exboard.list.adapter.persistence;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.ExBoard;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardElement;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardListFilterCommand;
import org.beizix.core.usecase.exboard.list.application.port.out.ExBoardListPortOut;
import org.beizix.core.usecase.file.upload.application.domain.FileUploadOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

@Repository
@RequiredArgsConstructor
class ExBoardListDao implements ExBoardListPortOut {
  private final ExBoardListRepo exBoardRepo;

  @Override
  public Page<ExBoardElement> connect(Pageable pageable, ExBoardListFilterCommand command) {
    // 검색조건 초기화
    Specification<ExBoard> spec = (root, query, criteriaBuilder) -> null;

    // 검색어 - like 검색
    if (!StringUtils.isEmpty(command.getSearchField())) {
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.like(
                      root.get(command.getSearchField()), "%" + command.getSearchValue() + "%"));
    }

    // 공개여부 검색
    if (!StringUtils.isEmpty(command.getSearchOpen())) {
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("visible"), Boolean.valueOf(command.getSearchOpen()))));
    }

    Page<ExBoard> result = exBoardRepo.findAll(spec, pageable);

    return new PageImpl<>(
        result.getContent().stream()
            .map(
                item ->
                    new ExBoardElement(
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
                        item.getRepresentImage() != null
                            ? new FileUploadOutput(
                                item.getRepresentImage().getType(),
                                item.getRepresentImage().getPath(),
                                item.getRepresentImage().getName(),
                                item.getRepresentImage().getOriginName(),
                                item.getRepresentImage().getFileLength())
                            : null,
                        item.getRepImgAlt(),
                        item.getOrderNo()))
            .collect(Collectors.toList()),
        pageable,
        result.getTotalElements());
  }
}
