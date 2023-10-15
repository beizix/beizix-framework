package org.beizix.security.adapter.persistence.role;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.beizix.security.adapter.persistence.role.model.Role;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.adapter.persistence.role_privilege.model.RoleWithPrivilege;
import org.beizix.security.application.port.out.role.RoleSavePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleSaveDao implements RoleSavePortOut {
  private final RoleRepo roleRepo;

  @Override
  public String connect(String id, String description, Integer orderNo, List<String> privilegeIds) {
    return roleRepo
        .save(
            new Role(
                id,
                description,
                orderNo,
                new LinkedHashSet<>(
                    CollectionUtils.emptyIfNull(privilegeIds).stream()
                        .map(pId -> new RoleWithPrivilege(null, new Role(id), new Privilege(pId)))
                        .collect(Collectors.toList()))))
        .getId();
  }
}
