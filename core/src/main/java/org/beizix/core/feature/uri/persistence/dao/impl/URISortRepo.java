package org.beizix.core.feature.uri.persistence.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.beizix.core.feature.uri.persistence.model.URIEntity;

public interface URISortRepo
    extends JpaRepository<URIEntity, String>, JpaSpecificationExecutor<URIEntity> {
  @Modifying
  @Query("update URIEntity m set m.orderNo = :orderNo where m.id = :id")
  void operate(@Param("id") String id, @Param("orderNo") Integer orderNo);
}
