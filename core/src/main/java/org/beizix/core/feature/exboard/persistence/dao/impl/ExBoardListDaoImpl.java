package org.beizix.core.feature.exboard.persistence.dao.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.exboard.application.model.ExBoard;
import org.beizix.core.feature.exboard.application.model.ExBoardListCondition;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardListDao;
import org.beizix.core.feature.exboard.persistence.model.ExBoardEntity;

@Repository
@RequiredArgsConstructor
class ExBoardListDaoImpl implements ExBoardListDao {
  private final ExBoardRepo exBoardRepo;
  private final ModelMapper modelMapper;

  @Override
  public Page<ExBoard> operate(Pageable pageable, ExBoardListCondition exBoardListCondition) {
    // 검색조건 초기화
    Specification<ExBoardEntity> spec = (root, query, criteriaBuilder) -> null;

    // 검색어 - like 검색
    if (!StringUtils.isEmpty(exBoardListCondition.getSearchField())) {
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.like(
                      root.get(exBoardListCondition.getSearchField()),
                      "%" + exBoardListCondition.getSearchValue() + "%"));
    }

    // 공개여부 검색
    if (!StringUtils.isEmpty(exBoardListCondition.getSearchOpen())) {
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("visible"), Boolean.valueOf(exBoardListCondition.getSearchOpen()))));
    }

    return exBoardRepo.findAll(spec, pageable).map(item -> modelMapper.map(item, ExBoard.class));
  }
}
