package app.module.admin.usecase.privilege.list.adapters.persistence;

import app.module.admin.config.adapter.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeListRepo  extends JpaRepository<Privilege, String> {}
