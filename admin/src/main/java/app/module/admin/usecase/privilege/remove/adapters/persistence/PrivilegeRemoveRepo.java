package app.module.admin.usecase.privilege.remove.adapters.persistence;

import app.module.admin.config.adapter.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRemoveRepo  extends JpaRepository<Privilege, String>{}
