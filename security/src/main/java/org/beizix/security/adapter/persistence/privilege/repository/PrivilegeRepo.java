package org.beizix.security.adapter.persistence.privilege.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;

public interface PrivilegeRepo extends JpaRepository<Privilege, String> {}
