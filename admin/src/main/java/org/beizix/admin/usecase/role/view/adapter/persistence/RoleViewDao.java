package org.beizix.admin.usecase.role.view.adapter.persistence;

import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.admin.usecase.role.view.application.port.out.RoleViewPortOut;
import org.beizix.admin.usecase.role.view.application.domain.RoleView;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleViewDao implements RoleViewPortOut<RoleView> {
  private final RoleViewRepo roleRepo;

  @Override
  @Transactional
  public RoleView connect(String roleId) {
    return roleRepo
        .findById(roleId)
        .map(
            role ->
                new RoleView(
                    role.getCreatedBy(),
                    role.getCreatedAt(),
                    role.getUpdatedBy(),
                    role.getUpdatedAt(),
                    role.getId(),
                    role.getDescription(),
                    role.getOrderNo(),
                    CollectionUtils.emptyIfNull(role.getWithPrivileges()).stream()
                        .map(wp -> wp.getPrivilege().getId())
                        .collect(Collectors.toList())))
        .orElse(null);
  }
}
