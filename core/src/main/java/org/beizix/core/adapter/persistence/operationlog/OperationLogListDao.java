package org.beizix.core.adapter.persistence.operationlog;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.operationlog.model.OperationLog;
import org.beizix.core.adapter.persistence.operationlog.repository.OperationLogRepo;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.common.model.PageableOutput;
import org.beizix.core.application.domain.operationlog.model.filter.OperationLogListStatus;
import org.beizix.core.application.domain.operationlog.model.list.OperationLogListOutput;
import org.beizix.core.application.domain.operationlog.model.list.OperationLogOutput;
import org.beizix.core.application.port.out.operationlog.OperationLogListPortOut;
import org.beizix.core.configuration.application.enums.AppType;
import org.beizix.core.configuration.application.enums.OperationLogType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

@Repository
@RequiredArgsConstructor
class OperationLogListDao implements OperationLogListPortOut {
  private final OperationLogRepo operationLogRepo;

  @Override
  public OperationLogListOutput connect(
      PageableInput pageableInput, OperationLogListStatus operationLogListStatus) {
    // 검색조건 초기화
    Specification<OperationLog> spec = (root, query, builder) -> null;

    // 검색어 - equals 검색
    if (!StringUtils.isEmpty(operationLogListStatus.getSearchField()))
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.equal(
                      root.get(operationLogListStatus.getSearchField()),
                      operationLogListStatus.getSearchValue()));

    // App Type 검색
    if (!StringUtils.isEmpty(operationLogListStatus.getSearchAppType()))
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("appType"),
                      AppType.valueOf(operationLogListStatus.getSearchAppType()))));

    // Operation Type 검색
    if (!StringUtils.isEmpty(operationLogListStatus.getSearchOperationType()))
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("operationLogType"),
                      OperationLogType.valueOf(operationLogListStatus.getSearchOperationType()))));

    PageRequest pageRequest =
        PageRequest.of(
            pageableInput.getPageNumber(),
            pageableInput.getPageSize(),
            Sort.by(
                Direction.fromString(pageableInput.getOrderDir().name()),
                pageableInput.getOrderBy()));

    Page<OperationLog> result = operationLogRepo.findAll(spec, pageRequest);
    Pageable pageable = result.getPageable();

    return new OperationLogListOutput(
        new PageableOutput(
            pageable.hasPrevious(),
            pageable.getPageNumber(),
            pageable.getPageSize(),
            pageableInput.getOrderBy(),
            pageableInput.getOrderDir(),
            result.getTotalElements(),
            result.getTotalPages()),
        result.getContent().stream()
            .map(
                op ->
                    new OperationLogOutput(
                        op.getCreatedBy(),
                        op.getCreatedAt(),
                        op.getId(),
                        op.getAppType(),
                        op.getOperationLogType(),
                        op.getTargetId(),
                        op.getIp(),
                        op.getTaskDesc()))
            .collect(Collectors.toList()));
  }
}
