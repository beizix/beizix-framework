package org.beizix.admin.usecase.uri.remove.adapter.persistence;

import org.beizix.core.configuration.adapter.persistence.entity.URI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URIRemoveRepo extends JpaRepository<URI, String> {}
