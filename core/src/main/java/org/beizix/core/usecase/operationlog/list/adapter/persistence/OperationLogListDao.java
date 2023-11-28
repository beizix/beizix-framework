package org.beizix.core.usecase.operationlog.list.adapter.persistence;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.core.config.adapter.persistence.entity.OperationLog;
import org.beizix.core.config.application.component.PageableInput;
import org.beizix.core.config.application.component.PageableOutput;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.config.application.enums.OperationLogType;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogElement;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogPageableList;
import org.beizix.core.usecase.operationlog.list.application.port.out.OperationLogListPortOut;
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
  private final OperationLogListRepo operationLogRepo;

  @Override
  public OperationLogPageableList connect(
      PageableInput pageableInput, OperationLogListFilterCommand operationLogListFilterCommand) {
    // 검색조건 초기화
    Specification<OperationLog> spec = (root, query, builder) -> null;

    // 검색어 - equals 검색
    if (!StringUtils.isEmpty(operationLogListFilterCommand.getSearchField()))
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.equal(
                      root.get(operationLogListFilterCommand.getSearchField()),
                      operationLogListFilterCommand.getSearchValue()));

    // App Type 검색
    if (!StringUtils.isEmpty(operationLogListFilterCommand.getSearchAppType()))
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("appType"),
                      AppType.valueOf(operationLogListFilterCommand.getSearchAppType()))));

    // Operation Type 검색
    if (!StringUtils.isEmpty(operationLogListFilterCommand.getSearchOperationType()))
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("operationLogType"),
                      OperationLogType.valueOf(operationLogListFilterCommand.getSearchOperationType()))));

    PageRequest pageRequest =
        PageRequest.of(
            pageableInput.getPageNumber(),
            pageableInput.getPageSize(),
            Sort.by(
                Direction.fromString(pageableInput.getOrderDir().name()),
                pageableInput.getOrderBy()));

    Page<OperationLog> result = operationLogRepo.findAll(spec, pageRequest);
    Pageable pageable = result.getPageable();

    return new OperationLogPageableList(
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
                    new OperationLogElement(
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
