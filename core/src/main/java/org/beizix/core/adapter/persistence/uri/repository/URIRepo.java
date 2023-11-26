package org.beizix.core.adapter.persistence.uri.repository;

import java.util.Optional;
import org.beizix.core.configuration.adapter.persistence.URI;
import org.beizix.core.configuration.application.enums.AppType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface URIRepo extends JpaRepository<URI, String>, JpaSpecificationExecutor<URI> {
  Optional<URI> findByAppTypeAndParentIdIsNull(AppType appType);
}
