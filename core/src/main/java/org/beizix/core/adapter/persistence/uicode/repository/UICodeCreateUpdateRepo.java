package org.beizix.core.adapter.persistence.uicode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.adapter.persistence.uicode.model.UICode;

public interface UICodeCreateUpdateRepo
    extends JpaRepository<UICode, String>, JpaSpecificationExecutor<UICode> {}
