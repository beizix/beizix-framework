package org.beizix.security.adapter.persistence.role;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.domain.role.model.list.PrivilegeOutput;
import org.beizix.security.application.domain.role.model.list.RoleOutput;
import org.beizix.security.application.port.out.role.RoleListPortOut;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleListDao implements RoleListPortOut<RoleOutput> {
  private final RoleRepo roleRepo;

  @Override
  @Transactional
  public List<RoleOutput> connect() {
    return roleRepo.findAll(Sort.by(Sort.Direction.ASC, "orderNo")).stream()
        .map(
            role ->
                new RoleOutput(
                    role.getCreatedBy(),
                    role.getCreatedAt(),
                    role.getUpdatedBy(),
                    role.getUpdatedAt(),
                    role.getId(),
                    role.getDescription(),
                    role.getOrderNo(),
                    CollectionUtils.emptyIfNull(role.getWithPrivileges()).stream()
                        .map(
                            w ->
                                new PrivilegeOutput(
                                    w.getPrivilege().getId(), w.getPrivilege().getDescription()))
                        .collect(Collectors.toList())))
        .collect(Collectors.toList());
  }
}
