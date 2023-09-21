package org.beizix.core.feature.uicode.persistence.dao.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.feature.uicode.persistence.model.UICodeEntity;

public interface UICodeRemoveRepo
    extends JpaRepository<UICodeEntity, String>, JpaSpecificationExecutor<UICodeEntity> {}
