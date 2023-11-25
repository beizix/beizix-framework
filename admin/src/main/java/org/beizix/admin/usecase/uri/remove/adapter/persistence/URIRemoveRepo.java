package org.beizix.admin.usecase.uri.remove.adapter.persistence;

import org.beizix.core.adapter.persistence.uri.model.URI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URIRemoveRepo extends JpaRepository<URI, String> {}
