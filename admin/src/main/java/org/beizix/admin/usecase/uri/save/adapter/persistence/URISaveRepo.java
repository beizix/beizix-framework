package org.beizix.admin.usecase.uri.save.adapter.persistence;

import org.beizix.core.configuration.adapter.persistence.entity.URI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URISaveRepo extends JpaRepository<URI, String> {}
