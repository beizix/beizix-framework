package org.beizix.admin.usecase.admin.list.adapter.persistence;

import org.beizix.security.adapter.persistence.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminListRepo
    extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {}
