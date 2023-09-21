package org.beizix.core.feature.uri.persistence.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.feature.uri.persistence.model.URIEntity;


public interface URIRepo
    extends JpaRepository<URIEntity, String>, JpaSpecificationExecutor<URIEntity> {}
