package app.module.admin.usecase.admin.save.adapters.persistence;

import app.module.admin.config.adapter.persistence.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminSaveRepo extends JpaRepository<Admin, String>{}
