package org.beizix.core.adapter.persistence.exboard;

import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListInput;
import org.beizix.core.application.port.out.exboard.ExBoardListPortOut;
import org.beizix.core.adapter.persistence.exboard.model.ExBoardEntity;

@Repository
@RequiredArgsConstructor
class ExBoardListDao implements ExBoardListPortOut {
  private final ExBoardRepo exBoardRepo;
  private final ModelMapper modelMapper;

  @Override
  public Page<ExBoardInput> connect(Pageable pageable, ExBoardListInput exBoardListInput) {
    // 검색조건 초기화
    Specification<ExBoardEntity> spec = (root, query, criteriaBuilder) -> null;

    // 검색어 - like 검색
    if (!StringUtils.isEmpty(exBoardListInput.getSearchField())) {
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.like(
                      root.get(exBoardListInput.getSearchField()),
                      "%" + exBoardListInput.getSearchValue() + "%"));
    }

    // 공개여부 검색
    if (!StringUtils.isEmpty(exBoardListInput.getSearchOpen())) {
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("visible"), Boolean.valueOf(exBoardListInput.getSearchOpen()))));
    }

    return exBoardRepo.findAll(spec, pageable).map(item -> modelMapper.map(item, ExBoardInput.class));
  }
}
