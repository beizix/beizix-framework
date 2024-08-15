package app.module.admin.usecase.privilege.view.adapters.persistence;

import app.module.admin.usecase.privilege.view.ports.PrivilegeViewPortOut;
import app.module.admin.usecase.privilege.view.ports.application.domain.PrivilegeView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeViewDao implements PrivilegeViewPortOut<PrivilegeView> {
  private final PrivilegeViewRepo privilegeRepo;

  @Override
  public PrivilegeView connect(String id) {
    return privilegeRepo
        .findById(id)
        .map(
            p ->
                new PrivilegeView(
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
