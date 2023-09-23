package org.beizix.core.adapter.persistence.uri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.config.enums.AppType;
import org.beizix.core.adapter.persistence.uri.model.URI;


public interface URIListRepo
    extends JpaRepository<URI, String>, JpaSpecificationExecutor<URI> {
  List<URI> findAllByAppType(AppType appType);
}
