package app.module.core.usecase.operationlog.list.adapter.persistence;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import app.module.core.config.adapter.persistence.entity.OperationLog;
import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.enums.OperationLogType;
import app.module.core.usecase.operationlog.list.application.domain.OperationLogElement;
import app.module.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import app.module.core.usecase.operationlog.list.application.port.out.OperationLogListPortOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

@Repository
@RequiredArgsConstructor
class OperationLogListDao implements OperationLogListPortOut {
  private final OperationLogListRepo operationLogRepo;

  @Override
  public Page<OperationLogElement> connect(
      Pageable pageable, OperationLogListFilterCommand operationLogListFilterCommand) {
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
                      OperationLogType.valueOf(
                          operationLogListFilterCommand.getSearchOperationType()))));

    Page<OperationLog> result = operationLogRepo.findAll(spec, pageable);

    return new PageImpl<>(
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
            .collect(Collectors.toList()),
        result.getPageable(),
        result.getTotalElements());
  }
}
