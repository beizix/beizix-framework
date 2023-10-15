package org.beizix.security.adapter.persistence.role;

import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.domain.role.model.view.RoleViewOutput;
import org.beizix.security.application.port.out.role.RoleViewPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleViewDao implements RoleViewPortOut<RoleViewOutput> {
  private final RoleRepo roleRepo;

  @Override
  @Transactional
  public RoleViewOutput connect(String roleId) {
    return roleRepo
        .findById(roleId)
        .map(
            role ->
                new RoleViewOutput(
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
