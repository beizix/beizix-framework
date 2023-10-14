package org.beizix.security.adapter.persistence.privilege;

import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.privilege.repository.PrivilegeRepo;
import org.beizix.security.application.domain.privilege.model.view.PrivilegeOutput;
import org.beizix.security.application.port.out.privilege.PrivilegeViewPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeViewDao implements PrivilegeViewPortOut<PrivilegeOutput> {
  private final PrivilegeRepo privilegeRepo;

  @Override
  public PrivilegeOutput connect(String id) {
    return privilegeRepo
        .findById(id)
        .map(
            p ->
                new PrivilegeOutput(
                    p.getCreatedBy(),
                    p.getCreatedAt(),
                    p.getUpdatedBy(),
                    p.getUpdatedAt(),
                    p.getId(),
                    p.getDescription(),
                    p.getOrderNo()))
        .orElse(null);
  }
}
