package org.beizix.admin.usecase.admin.list.adapter.persistence;

import org.beizix.admin.configuration.adapter.persistence.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminListRepo
    extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {}
