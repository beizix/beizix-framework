package org.beizix.admin.usecase.admin.save.adapter.persistence;

import org.beizix.admin.config.adapter.persistence.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminSaveRepo extends JpaRepository<Admin, String>{}
