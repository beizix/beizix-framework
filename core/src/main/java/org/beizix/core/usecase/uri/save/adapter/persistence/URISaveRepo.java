package org.beizix.core.usecase.uri.save.adapter.persistence;

import org.beizix.core.config.adapter.persistence.entity.URI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URISaveRepo extends JpaRepository<URI, String> {}
