package app.module.admin.usecase.admin.list.adapters.persistence;

import app.module.admin.config.adapter.persistence.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminListRepo
    extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {}
