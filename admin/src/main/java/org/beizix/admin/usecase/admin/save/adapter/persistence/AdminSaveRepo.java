package org.beizix.admin.usecase.admin.save.adapter.persistence;

import org.beizix.security.adapter.persistence.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminSaveRepo extends JpaRepository<Admin, String>{}
