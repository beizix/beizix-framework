package org.beizix.admin.usecase.admin.list.adapter.persistence;

import java.util.stream.Collectors;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.configuration.adapter.persistence.entity.Admin;
import org.beizix.admin.configuration.adapter.persistence.entity.AdminWithRole_;
import org.beizix.admin.configuration.adapter.persistence.entity.Admin_;
import org.beizix.admin.configuration.adapter.persistence.entity.Privilege;
import org.beizix.admin.configuration.adapter.persistence.entity.Role;
import org.beizix.admin.configuration.adapter.persistence.entity.Role_;
import org.beizix.admin.usecase.admin.list.application.domain.AdminElement;
import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.beizix.admin.usecase.admin.list.application.domain.AdminPageableList;
import org.beizix.admin.usecase.admin.list.application.domain.PrivilegeElement;
import org.beizix.admin.usecase.admin.list.application.domain.RoleElement;
import org.beizix.admin.usecase.admin.list.application.port.out.AdminListPortOut;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.common.model.PageableOutput;
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
  private final AdminListRepo adminRepo;

  @Override
  @Transactional
  public AdminPageableList connect(
      PageableInput pageableInput, AdminListFilterCommand filterCommand) {
    // 검색조건 초기화
    Specification<Admin> spec = (root, query, criteriaBuilder) -> null;

    // 검색어 - like 검색
    if (!StringUtils.isEmpty(filterCommand.getSearchField())) {
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.like(
                      root.get(filterCommand.getSearchField()),
                      "%" + filterCommand.getSearchValue() + "%"));
    }

    // 권한 검색 - Join 절 검색이기에 List 순회가 필요하다.
    if (!StringUtils.isEmpty(filterCommand.getSearchRole())) {
      spec =
          spec.and(
              (root, query, builder) -> {
                Join<Object, Object> join = root.join(Admin_.WITH_ROLES, JoinType.INNER);
                return builder.equal(
                    join.get(AdminWithRole_.ROLE).get(Role_.ID), filterCommand.getSearchRole());
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

    return new AdminPageableList(
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
                    new AdminElement(
                        admin.getCreatedBy(),
                        admin.getCreatedAt(),
                        admin.getUpdatedBy(),
                        admin.getUpdatedAt(),
                        admin.getId(),
                        admin.getEmail(),
                        admin.getAccountDisabled(),
                        admin.getAccountDisabled(),
                        admin.getWithRoles().stream()
                            .map(
                                withRole -> {
                                  Role role = withRole.getRole();
                                  return new RoleElement(
                                      role.getId(),
                                      role.getDescription(),
                                      role.getOrderNo(),
                                      role.getWithPrivileges().stream()
                                          .map(
                                              withPrivilege -> {
                                                Privilege privilege = withPrivilege.getPrivilege();
                                                return new PrivilegeElement(privilege.getId());
                                              })
                                          .collect(Collectors.toList()));
                                })
                            .collect(Collectors.toList())))
            .collect(Collectors.toList()));
  }
}
