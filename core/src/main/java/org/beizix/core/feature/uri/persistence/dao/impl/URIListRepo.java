package org.beizix.core.feature.uri.persistence.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.persistence.model.URIEntity;


public interface URIListRepo
    extends JpaRepository<URIEntity, String>, JpaSpecificationExecutor<URIEntity> {
  List<URIEntity> findAllByAppType(AppType appType);
}
