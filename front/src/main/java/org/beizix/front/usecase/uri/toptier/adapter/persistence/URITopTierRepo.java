package org.beizix.front.usecase.uri.toptier.adapter.persistence;

import java.util.Optional;
import org.beizix.core.adapter.persistence.uri.model.URI;
import org.beizix.core.config.enums.AppType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URITopTierRepo extends JpaRepository<URI, String> {

  Optional<URI> findByAppTypeAndParentIdIsNull(AppType appType);
}
