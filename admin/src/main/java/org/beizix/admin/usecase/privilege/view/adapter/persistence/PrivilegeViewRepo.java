package org.beizix.admin.usecase.privilege.view.adapter.persistence;

import org.beizix.admin.config.adapter.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeViewRepo  extends JpaRepository<Privilege, String> {}
