package org.beizix.core.adapter.persistence.uri.repository;

import java.util.List;
import org.beizix.core.adapter.persistence.uri.model.URI;
import org.beizix.core.config.enums.AppType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface URIListRepo extends JpaRepository<URI, String>, JpaSpecificationExecutor<URI> {
  List<URI> findAllByAppType(AppType appType);
}
