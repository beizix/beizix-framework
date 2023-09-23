package org.beizix.security.adapter.persistence.admin;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;
import org.beizix.security.adapter.persistence.admin.model.Admin;
import org.beizix.security.adapter.persistence.admin.model.Admin_;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.adapter.persistence.admin_role.model.AdminWithRole_;
import org.beizix.security.adapter.persistence.role.model.Role_;
import org.beizix.security.application.domain.admin.model.list.AdminListOutput;
import org.beizix.security.application.domain.admin.model.filter.AdminListInput;
import org.beizix.security.application.port.out.admin.AdminListPortOut;

@Component
@RequiredArgsConstructor
class AdminListDao implements AdminListPortOut {
  private final AdminRepo adminRepo;
  private final ModelMapper modelMapper;

  @Override
  @Transactional
  public Page<AdminListOutput> connect(
      Pageable pageable, AdminListInput adminListInput) {
    // 검색조건 초기화
    Specification<Admin> spec = (root, query, criteriaBuilder) -> null;

    // 검색어 - like 검색
    if (!StringUtils.isEmpty(adminListInput.getSearchField())) {
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.like(
                      root.get(adminListInput.getSearchField()),
                      "%" + adminListInput.getSearchValue() + "%"));
    }

    // 권한 검색 - Join 절 검색이기에 List 순회가 필요하다.
    if (!StringUtils.isEmpty(adminListInput.getSearchRole())) {
      spec =
          spec.and(
              (root, query, builder) -> {
                Join<Object, Object> join = root.join(Admin_.WITH_ROLES, JoinType.INNER);
                return builder.equal(
                    join.get(AdminWithRole_.ROLE).get(Role_.ID),
                    adminListInput.getSearchRole());
              });
    }

    Page<Admin> result = adminRepo.findAll(spec, pageable);
    return result.map(v -> modelMapper.map(v, AdminListOutput.class));
  }
}
