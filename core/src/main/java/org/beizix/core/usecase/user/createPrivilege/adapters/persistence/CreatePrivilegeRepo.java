package org.beizix.core.usecase.user.createPrivilege.adapters.persistence;

import org.beizix.core.config.adapter.persistence.entity.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatePrivilegeRepo extends JpaRepository<UserPrivilege, String> {}
