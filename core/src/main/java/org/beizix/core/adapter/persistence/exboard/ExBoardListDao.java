package org.beizix.core.adapter.persistence.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;
import org.beizix.core.application.port.out.exboard.ExBoardListPortOut;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

@Repository
@RequiredArgsConstructor
class ExBoardListDao implements ExBoardListPortOut {
  private final ExBoardRepo exBoardRepo;
  private final ModelMapper modelMapper;

  @Override
  public Page<ExBoardListOutput> connect(
      Pageable pageable, ExBoardListFilterInput exBoardListFilterInput) {
    // 검색조건 초기화
    Specification<ExBoard> spec = (root, query, criteriaBuilder) -> null;

    // 검색어 - like 검색
    if (!StringUtils.isEmpty(exBoardListFilterInput.getSearchField())) {
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.like(
                      root.get(exBoardListFilterInput.getSearchField()),
                      "%" + exBoardListFilterInput.getSearchValue() + "%"));
    }

    // 공개여부 검색
    if (!StringUtils.isEmpty(exBoardListFilterInput.getSearchOpen())) {
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("visible"),
                      Boolean.valueOf(exBoardListFilterInput.getSearchOpen()))));
    }

    return exBoardRepo
        .findAll(spec, pageable)
        .map(item -> modelMapper.map(item, ExBoardListOutput.class));
  }
}
