package app.module.admin.usecase.user.getUsers.adapters.persistence;

import app.module.admin.usecase.user.getUsers.ports.GetUserListPortOut;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsers;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsersCmd;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsersPrivilege;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsersRole;
import app.module.core.config.adapter.persistence.entity.FrontUser;
import app.module.core.config.adapter.persistence.entity.FrontUser_;
import app.module.core.config.adapter.persistence.entity.UserRole_;
import app.module.core.config.adapter.persistence.entity.UserWithUserRole_;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
class GetUserListDao implements GetUserListPortOut {
  private final GetUserListRepo getUserListRepo;

  @Override
  @Transactional
  public Page<GetUsers> operate(Pageable pageable, GetUsersCmd command) {
    // 검색조건 초기화
    Specification<FrontUser> spec = (root, query, criteriaBuilder) -> null;

    // 검색어 - like 검색
    if (!StringUtils.isEmpty(command.getSearchField())) {
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.like(
                      root.get(command.getSearchField()), "%" + command.getSearchValue() + "%"));
    }

    // 권한 검색 - Join 절 검색이기에 List 순회가 필요하다.
    if (!StringUtils.isEmpty(command.getSearchRole())) {
      spec =
          spec.and(
              (root, query, builder) -> {
                Join<Object, Object> join = root.join(FrontUser_.WITH_USER_ROLES, JoinType.INNER);
                return builder.equal(
                    join.get(UserWithUserRole_.ROLE).get(UserRole_.ID), command.getSearchRole());
              });
    }

    Page<FrontUser> result = getUserListRepo.findAll(spec, pageable);

    return new PageImpl<>(
        result.getContent().stream()
            .map(
                frontUser ->
                    new GetUsers(
                        frontUser.getId(),
                        frontUser.getEmail(),
                        frontUser.getPasswordUpdatedAt(),
                        frontUser.getAccountDisabled(),
                        frontUser.getLoginFailCnt(),
                        frontUser.getAccountLocked(),
                        frontUser.getCreatedAt(),
                        frontUser.getCreatedBy(),
                        frontUser.getUpdatedAt(),
                        frontUser.getUpdatedBy(),
                        frontUser.getWithUserRoles().stream()
                            .map(
                                userWithUserRole ->
                                    new GetUsersRole(
                                        userWithUserRole.getRole().getId(),
                                        userWithUserRole.getRole().getDescription(),
                                        userWithUserRole.getRole().getWithUserPrivileges().stream()
                                            .map(
                                                userRoleWithUserPrivilege ->
                                                    new GetUsersPrivilege(
                                                        userRoleWithUserPrivilege
                                                            .getUserPrivilege()
                                                            .getId(),
                                                        userRoleWithUserPrivilege
                                                            .getUserPrivilege()
                                                            .getDescription()))
                                            .collect(Collectors.toSet())))
                            .collect(Collectors.toSet())))
            .collect(Collectors.toList()),
        result.getPageable(),
        result.getTotalElements());
  }
}
