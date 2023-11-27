package org.beizix.admin.usecase.admin.remove.adapter.persistence;

import org.beizix.security.adapter.persistence.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRemoveRepo extends JpaRepository<Admin, String>{}
