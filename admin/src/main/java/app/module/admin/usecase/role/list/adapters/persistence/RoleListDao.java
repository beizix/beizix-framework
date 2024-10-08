package app.module.admin.usecase.role.list.adapters.persistence;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import app.module.admin.usecase.role.list.ports.RoleListPortOut;
import app.module.admin.usecase.role.list.ports.application.domain.PrivilegeElement;
import app.module.admin.usecase.role.list.ports.application.domain.RoleElement;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleListDao implements RoleListPortOut<RoleElement> {
  private final RoleListRepo roleRepo;

  @Override
  @Transactional
  public List<RoleElement> connect() {
    return roleRepo.findAll(Sort.by(Sort.Direction.ASC, "orderNo")).stream()
        .map(
            role ->
                new RoleElement(
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
                                new PrivilegeElement(
                                    w.getPrivilege().getId(), w.getPrivilege().getDescription()))
                        .collect(Collectors.toList())))
        .collect(Collectors.toList());
  }
}
