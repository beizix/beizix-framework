package app.module.admin.usecase.privilege.view.adapters.persistence;

import app.module.admin.config.adapter.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeViewRepo  extends JpaRepository<Privilege, String> {}
