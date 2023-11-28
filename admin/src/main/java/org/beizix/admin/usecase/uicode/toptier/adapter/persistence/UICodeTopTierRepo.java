package org.beizix.admin.usecase.uicode.toptier.adapter.persistence;

import org.beizix.core.config.adapter.persistence.entity.UICode;
import org.springframework.data.jpa.repository.JpaRepository;

interface UICodeTopTierRepo extends JpaRepository<UICode, String> {
  UICode findByParentIdIsNull();
}
