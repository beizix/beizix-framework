package org.beizix.admin.usecase.uicode.save.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.adapter.persistence.uicode.model.UICode;

public interface UICodeSaveRepo
    extends JpaRepository<UICode, String>, JpaSpecificationExecutor<UICode> {}
