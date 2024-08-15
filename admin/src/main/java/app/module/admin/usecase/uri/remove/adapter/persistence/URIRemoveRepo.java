package app.module.admin.usecase.uri.remove.adapter.persistence;

import app.module.core.config.adapter.persistence.entity.URI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URIRemoveRepo extends JpaRepository<URI, String> {}
