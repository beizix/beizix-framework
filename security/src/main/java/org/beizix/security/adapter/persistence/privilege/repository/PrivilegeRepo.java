package org.beizix.security.adapter.persistence.privilege.repository;

import java.util.Optional;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrivilegeRepo extends JpaRepository<Privilege, String> {



}
