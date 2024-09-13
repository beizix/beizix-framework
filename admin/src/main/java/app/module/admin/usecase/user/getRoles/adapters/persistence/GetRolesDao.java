package app.module.admin.usecase.user.getRoles.adapters.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.user.getRoles.ports.application.domain.GetRoles;
import app.module.admin.usecase.user.getRoles.ports.application.domain.GetRolesCmd;
import app.module.admin.usecase.user.getRoles.ports.GetRolesPortOut;
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
