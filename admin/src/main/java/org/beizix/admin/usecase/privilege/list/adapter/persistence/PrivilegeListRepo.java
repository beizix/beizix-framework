package org.beizix.admin.usecase.privilege.list.adapter.persistence;

import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeListRepo  extends JpaRepository<Privilege, String> {}
