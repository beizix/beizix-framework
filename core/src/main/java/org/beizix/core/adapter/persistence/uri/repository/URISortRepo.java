package org.beizix.core.adapter.persistence.uri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.beizix.core.adapter.persistence.uri.model.URI;

public interface URISortRepo
    extends JpaRepository<URI, String>, JpaSpecificationExecutor<URI> {
  @Modifying
  @Query("update URI m set m.orderNo = :orderNo where m.id = :id")
  void operate(@Param("id") String id, @Param("orderNo") Integer orderNo);
}
