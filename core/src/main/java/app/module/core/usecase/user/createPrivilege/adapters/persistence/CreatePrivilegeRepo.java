package app.module.core.usecase.user.createPrivilege.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatePrivilegeRepo extends JpaRepository<UserPrivilege, String> {}
