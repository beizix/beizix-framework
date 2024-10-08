package app.module.admin.usecase.uicode.save.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.module.core.config.adapter.persistence.entity.UICode;

public interface UICodeSaveRepo
    extends JpaRepository<UICode, String>, JpaSpecificationExecutor<UICode> {}
