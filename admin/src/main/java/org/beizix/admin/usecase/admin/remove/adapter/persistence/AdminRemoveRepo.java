package org.beizix.admin.usecase.admin.remove.adapter.persistence;

import org.beizix.admin.config.adapter.persistence.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRemoveRepo extends JpaRepository<Admin, String>{}
