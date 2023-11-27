package org.beizix.admin.usecase.role.save.adapter.persistence;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.admin.usecase.role.save.application.port.out.RoleSavePortOut;
import org.beizix.admin.configuration.adapter.persistence.entity.Privilege;
import org.beizix.admin.configuration.adapter.persistence.entity.Role;
import org.beizix.admin.configuration.adapter.persistence.entity.RoleWithPrivilege;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleSaveDao implements RoleSavePortOut {
  private final RoleSaveRepo roleRepo;

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
