package org.beizix.security.adapter.persistence.admin;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.common.model.PageableOutput;
import org.beizix.security.adapter.persistence.admin.model.Admin;
import org.beizix.security.adapter.persistence.admin.model.Admin_;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.adapter.persistence.admin_role.model.AdminWithRole_;
import org.beizix.security.adapter.persistence.role.model.Role_;
import org.beizix.security.application.domain.admin.model.filter.AdminListStatus;
import org.beizix.security.application.domain.admin.model.list.AdminListOutput;
import org.beizix.security.application.domain.admin.model.list.AdminOutput;
import org.beizix.security.application.domain.admin.model.list.RoleOutput;
import org.beizix.security.application.domain.admin.model.list.WithRoleOutput;
import org.beizix.security.application.port.out.admin.AdminListPortOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
@RequiredArgsConstructor
class AdminListDao implements AdminListPortOut {
  private final AdminRepo adminRepo;

  @Override
  @Transactional
  public AdminListOutput connect(PageableInput pageableInput, AdminListStatus adminListStatus) {
    // 검색조건 초기화
    Specification<Admin> spec = (root, query, criteriaBuilder) -> null;

    // 검색어 - like 검색
    if (!StringUtils.isEmpty(adminListStatus.getSearchField())) {
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.like(
                      root.get(adminListStatus.getSearchField()),
                      "%" + adminListStatus.getSearchValue() + "%"));
    }

    // 권한 검색 - Join 절 검색이기에 List 순회가 필요하다.
    if (!StringUtils.isEmpty(adminListStatus.getSearchRole())) {
      spec =
          spec.and(
              (root, query, builder) -> {
                Join<Object, Object> join = root.join(Admin_.WITH_ROLES, JoinType.INNER);
                return builder.equal(
                    join.get(AdminWithRole_.ROLE).get(Role_.ID), adminListStatus.getSearchRole());
              });
    }

    PageRequest pageRequest =
        PageRequest.of(
            pageableInput.getPageNumber(),
            pageableInput.getPageSize(),
            Sort.by(
                Direction.fromString(pageableInput.getOrderDir().name()),
                pageableInput.getOrderBy()));

    Page<Admin> result = adminRepo.findAll(spec, pageRequest);
    Pageable pageable = result.getPageable();

    return new AdminListOutput(
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
                admin ->
                    new AdminOutput(
                        admin.getCreatedBy(),
                        admin.getCreatedAt(),
                        admin.getUpdatedBy(),
                        admin.getUpdatedAt(),
                        admin.getId(),
                        admin.getEmail(),
                        admin.getAccountDisabled(),
                        admin.getAccountDisabled(),
                        new LinkedHashSet<>(
                            admin.getWithRoles().stream()
                                .map(
                                    with ->
                                        new WithRoleOutput(
                                            with.getId(),
                                            new RoleOutput(
                                                with.getRole().getId(),
                                                with.getRole().getDescription(),
                                                with.getRole().getOrderNo(),
                                                null)))
                                .collect(Collectors.toList()))))
            .collect(Collectors.toList()));
  }
}
