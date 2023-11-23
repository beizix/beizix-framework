package org.beizix.admin.usecases.uicode.toptier.adapter.persistence;

import org.beizix.core.adapter.persistence.uicode.model.UICode;
import org.springframework.data.jpa.repository.JpaRepository;

interface UICodeTopTierRepo extends JpaRepository<UICode, String> {
  UICode findByParentIdIsNull();
}
