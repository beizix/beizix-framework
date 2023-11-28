package org.beizix.admin.usecase.privilege.list.adapter.persistence;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.list.application.port.out.PrivilegeListPortOut;
import org.beizix.admin.config.adapter.persistence.entity.Privilege;
import org.beizix.admin.usecase.privilege.list.application.domain.PrivilegeElement;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeListDao implements PrivilegeListPortOut<PrivilegeElement> {
  private final PrivilegeListRepo privilegeRepo;

  @Override
  public List<PrivilegeElement> connect() {
    List<Privilege> result = privilegeRepo.findAll(Sort.by(Direction.ASC, "orderNo"));
    return result.stream()
        .map(
            privilege ->
                new PrivilegeElement(
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
