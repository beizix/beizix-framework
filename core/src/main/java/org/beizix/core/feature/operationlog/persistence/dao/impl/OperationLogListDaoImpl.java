package org.beizix.core.feature.operationlog.persistence.dao.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.enums.OperationLogType;
import org.beizix.core.feature.operationlog.application.model.OperationLog;
import org.beizix.core.feature.operationlog.application.model.OperationLogListCondition;
import org.beizix.core.feature.operationlog.persistence.dao.OperationLogListDao;
import org.beizix.core.feature.operationlog.persistence.model.OperationLogEntity;

@Repository
@RequiredArgsConstructor
class OperationLogListDaoImpl implements OperationLogListDao {
  private final OperationLogRepo operationLogRepo;
  private final ModelMapper modelMapper;

  @Override
  public Page<OperationLog> operate(
      Pageable pageable, OperationLogListCondition operationLogListCondition) {
    // 검색조건 초기화
    Specification<OperationLogEntity> spec = (root, query, builder) -> null;

    // 검색어 - equals 검색
    if (!StringUtils.isEmpty(operationLogListCondition.getSearchField()))
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.equal(
                      root.get(operationLogListCondition.getSearchField()),
                      operationLogListCondition.getSearchValue()));

    // App Type 검색
    if (!StringUtils.isEmpty(operationLogListCondition.getSearchAppType()))
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("appType"),
                      AppType.valueOf(operationLogListCondition.getSearchAppType()))));

    // Operation Type 검색
    if (!StringUtils.isEmpty(operationLogListCondition.getSearchOperationType()))
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("operationType"),
                      OperationLogType.valueOf(operationLogListCondition.getSearchOperationType()))));

    return operationLogRepo
        .findAll(spec, pageable)
        .map(item -> modelMapper.map(item, OperationLog.class));
  }
}
