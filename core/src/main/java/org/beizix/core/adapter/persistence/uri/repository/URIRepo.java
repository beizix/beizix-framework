package org.beizix.core.adapter.persistence.uri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.adapter.persistence.uri.model.URI;


public interface URIRepo
    extends JpaRepository<URI, String>, JpaSpecificationExecutor<URI> {}
