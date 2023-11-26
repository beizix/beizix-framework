package org.beizix.admin.usecase.uicode.save.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.configuration.adapter.persistence.UICode;

public interface UICodeSaveRepo
    extends JpaRepository<UICode, String>, JpaSpecificationExecutor<UICode> {}
