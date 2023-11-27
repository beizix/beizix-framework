package org.beizix.admin.usecase.privilege.remove.adapter.persistence;

import org.beizix.admin.configuration.adapter.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRemoveRepo  extends JpaRepository<Privilege, String>{}
