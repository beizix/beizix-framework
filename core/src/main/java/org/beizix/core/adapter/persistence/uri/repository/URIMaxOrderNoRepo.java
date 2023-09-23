package org.beizix.core.adapter.persistence.uri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.beizix.core.adapter.persistence.uri.model.URI;


public interface URIMaxOrderNoRepo
    extends JpaRepository<URI, String>, JpaSpecificationExecutor<URI> {

  @Query("select max(u.orderNo) from URI u where u.parentId = :parentId")
  Optional<Integer> getMaxOrderNo(String parentId);
}
