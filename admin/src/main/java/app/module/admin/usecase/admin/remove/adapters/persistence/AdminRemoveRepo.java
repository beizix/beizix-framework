package app.module.admin.usecase.admin.remove.adapters.persistence;

import app.module.admin.config.adapter.persistence.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRemoveRepo extends JpaRepository<Admin, String>{}
