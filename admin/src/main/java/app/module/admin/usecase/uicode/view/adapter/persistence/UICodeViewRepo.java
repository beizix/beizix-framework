package app.module.admin.usecase.uicode.view.adapter.persistence;

import app.module.core.config.adapter.persistence.entity.UICode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UICodeViewRepo extends JpaRepository<UICode, String> {}
