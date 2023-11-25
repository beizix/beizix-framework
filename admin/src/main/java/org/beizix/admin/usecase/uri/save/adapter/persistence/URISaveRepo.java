package org.beizix.admin.usecase.uri.save.adapter.persistence;

import org.beizix.core.adapter.persistence.uri.model.URI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URISaveRepo extends JpaRepository<URI, String> {}
