package app.module.admin.usecase.user.role.list.adapters.persistence;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.user.role.list.ports.application.domain.GetRoles;
import app.module.admin.usecase.user.role.list.ports.application.domain.GetRolesCmd;
import app.module.admin.usecase.user.role.list.ports.GetRolesPortOut;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class GetRolesDao implements GetRolesPortOut {
  private final GetRolesRepo getRolesRepo;

  @Override
  public List<GetRoles> operate(GetRolesCmd command) {
    return getRolesRepo.findAll().stream()
        .map(
            userRole ->
                new GetRoles(
                    userRole.getId(),
                    userRole.getDescription(),
                    userRole.getOrderNo(),
                    userRole.getCreatedAt(),
                    userRole.getCreatedBy(),
                    userRole.getUpdatedAt(),
                    userRole.getUpdatedBy()))
        .collect(Collectors.toList());
  }
}
