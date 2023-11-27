package org.beizix.admin.usecase.uri.save.adapter.persistence;

import java.util.Optional;
import org.beizix.core.configuration.adapter.persistence.entity.URI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface URIMaxOrderNoRepo extends JpaRepository<URI, String> {

  @Query("select max(u.orderNo) from URI u where u.parentId = :parentId")
  Optional<Integer> getMaxOrderNo(String parentId);
}
