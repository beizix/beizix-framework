package app.module.admin.usecase.admin.view.adapters.persistence;

import java.util.Optional;
import app.module.admin.config.adapter.persistence.entity.Admin;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminViewRepo extends JpaRepository<Admin, String> {
  @EntityGraph("fetch_roles")
  Optional<Admin> findAdminUserById(String id);
}
