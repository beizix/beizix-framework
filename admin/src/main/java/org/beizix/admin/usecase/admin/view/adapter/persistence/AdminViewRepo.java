package org.beizix.admin.usecase.admin.view.adapter.persistence;

import java.util.Optional;
import org.beizix.security.adapter.persistence.admin.model.Admin;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminViewRepo extends JpaRepository<Admin, String> {
  @EntityGraph("view_details_entity_graph")
  Optional<Admin> findAdminUserById(String id);
}
