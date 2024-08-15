package app.module.admin.usecase.uicode.toptier.adapter.persistence;

import app.module.core.config.adapter.persistence.entity.UICode;
import org.springframework.data.jpa.repository.JpaRepository;

interface UICodeTopTierRepo extends JpaRepository<UICode, String> {
  UICode findByParentIdIsNull();
}
