package app.module.admin.usecase.admin.list.adapters.persistence;

import java.util.stream.Collectors;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import app.module.admin.config.adapter.persistence.entity.Admin;
import app.module.admin.config.adapter.persistence.entity.AdminWithRole_;
import app.module.admin.config.adapter.persistence.entity.Admin_;
import app.module.admin.config.adapter.persistence.entity.Privilege;
import app.module.admin.config.adapter.persistence.entity.Role;
import app.module.admin.config.adapter.persistence.entity.Role_;
import app.module.admin.usecase.admin.list.ports.AdminListPortOut;
import app.module.admin.usecase.admin.list.ports.application.domain.AdminElement;
import app.module.admin.usecase.admin.list.ports.application.domain.AdminListCmd;
import app.module.admin.usecase.admin.list.ports.application.domain.PrivilegeElement;
import app.module.admin.usecase.admin.list.ports.application.domain.RoleElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

@Repository
@RequiredArgsConstructor
class AdminListDao implements AdminListPortOut {
  private final AdminListRepo adminRepo;

  @Override
  @Transactional
  public Page<AdminElement> connect(Pageable pageable, AdminListCmd filterCommand) {
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

    Page<Admin> result = adminRepo.findAll(spec, pageable);

    return new PageImpl<>(
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
            .collect(Collectors.toList()),
        result.getPageable(),
        result.getTotalElements());
  }
}
