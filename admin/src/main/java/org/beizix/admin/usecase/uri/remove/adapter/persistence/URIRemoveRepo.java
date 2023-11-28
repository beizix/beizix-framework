package org.beizix.admin.usecase.uri.remove.adapter.persistence;

import org.beizix.core.config.adapter.persistence.entity.URI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URIRemoveRepo extends JpaRepository<URI, String> {}
