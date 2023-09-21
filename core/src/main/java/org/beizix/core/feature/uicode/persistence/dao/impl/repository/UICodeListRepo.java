package org.beizix.core.feature.uicode.persistence.dao.impl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.beizix.core.feature.uicode.persistence.model.UICodeEntity;

public interface UICodeListRepo
    extends JpaRepository<UICodeEntity, String>, JpaSpecificationExecutor<UICodeEntity> {
  @Query("select e from UICodeEntity e where e.parentId = :parentId")
  List<UICodeEntity> operate(String parentId);
}
