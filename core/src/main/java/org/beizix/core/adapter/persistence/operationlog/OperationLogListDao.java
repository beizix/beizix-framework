package org.beizix.core.adapter.persistence.operationlog;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.operationlog.repository.OperationLogRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.enums.OperationLogType;
import org.beizix.core.application.domain.operationLog.model.filter.OperationLogListInput;
import org.beizix.core.application.port.out.operationlog.OperationLogListPortOut;
import org.beizix.core.adapter.persistence.operationlog.model.OperationLog;

@Repository
@RequiredArgsConstructor
class OperationLogListDao implements OperationLogListPortOut {
  private final OperationLogRepo operationLogRepo;
  private final ModelMapper modelMapper;

  @Override
  public Page<org.beizix.core.application.domain.operationLog.model.OperationLog> connect(
      Pageable pageable, OperationLogListInput operationLogListInput) {
    // 검색조건 초기화
    Specification<OperationLog> spec = (root, query, builder) -> null;

    // 검색어 - equals 검색
    if (!StringUtils.isEmpty(operationLogListInput.getSearchField()))
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.equal(
                      root.get(operationLogListInput.getSearchField()),
                      operationLogListInput.getSearchValue()));

    // App Type 검색
    if (!StringUtils.isEmpty(operationLogListInput.getSearchAppType()))
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("appType"),
                      AppType.valueOf(operationLogListInput.getSearchAppType()))));

    // Operation Type 검색
    if (!StringUtils.isEmpty(operationLogListInput.getSearchOperationType()))
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("operationType"),
                      OperationLogType.valueOf(operationLogListInput.getSearchOperationType()))));

    return operationLogRepo
        .findAll(spec, pageable)
        .map(item -> modelMapper.map(item, org.beizix.core.application.domain.operationLog.model.OperationLog.class));
  }
}
