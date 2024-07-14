package org.beizix.admin.usecase.privilege.remove.adapters.persistence;

import org.beizix.admin.config.adapter.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRemoveRepo  extends JpaRepository<Privilege, String>{}
