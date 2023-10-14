package org.beizix.security.adapter.persistence.privilege;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.beizix.security.adapter.persistence.privilege.model.Privilege_;
import org.beizix.security.adapter.persistence.privilege.repository.PrivilegeRepo;
import org.beizix.security.application.domain.privilege.model.list.PrivilegeOutput;
import org.beizix.security.application.port.out.privilege.PrivilegeListPortOut;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeListDao implements PrivilegeListPortOut<PrivilegeOutput> {
  private final PrivilegeRepo privilegeRepo;

  @Override
  public List<PrivilegeOutput> connect() {
    List<Privilege> result = privilegeRepo.findAll(Sort.by(Direction.ASC, "orderNo"));
    return result.stream()
        .map(
            privilege ->
                new PrivilegeOutput(
                    privilege.getCreatedBy(),
                    privilege.getCreatedAt(),
                    privilege.getUpdatedBy(),
                    privilege.getUpdatedAt(),
                    privilege.getId(),
                    privilege.getDescription(),
                    privilege.getOrderNo()))
        .collect(Collectors.toList());
  }
}
