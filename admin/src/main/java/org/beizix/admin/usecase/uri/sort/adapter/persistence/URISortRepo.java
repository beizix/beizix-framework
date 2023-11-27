package org.beizix.admin.usecase.uri.sort.adapter.persistence;

import org.beizix.core.configuration.adapter.persistence.entity.URI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface URISortRepo extends JpaRepository<URI, String> {
  @Modifying
  @Query("update URI m set m.orderNo = :orderNo where m.id = :id")
  void operate(@Param("id") String id, @Param("orderNo") Integer orderNo);
}
