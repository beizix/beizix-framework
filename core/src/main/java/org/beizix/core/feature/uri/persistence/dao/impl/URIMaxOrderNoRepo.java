package org.beizix.core.feature.uri.persistence.dao.impl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.beizix.core.feature.uri.persistence.model.URIEntity;


public interface URIMaxOrderNoRepo
    extends JpaRepository<URIEntity, String>, JpaSpecificationExecutor<URIEntity> {

  @Query("select max(u.orderNo) from URIEntity u where u.parentId = :parentId")
  Optional<Integer> getMaxOrderNo(String parentId);
}
