package app.module.core.usecase.uri.toptier.adapter.persistence;

import java.util.Optional;

import app.module.core.config.adapter.persistence.entity.URI;
import app.module.core.config.application.enums.AppType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URITopTierRepo extends JpaRepository<URI, String> {

  Optional<URI> findByAppTypeAndParentIdIsNull(AppType appType);
}
