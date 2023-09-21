package org.beizix.core.feature.uicode.persistence.dao.impl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.beizix.core.feature.uicode.persistence.model.UICodeEntity;

public interface UICodeMaxOrderNoRepo
    extends JpaRepository<UICodeEntity, String>, JpaSpecificationExecutor<UICodeEntity> {
  @Query("select max(u.orderNo) from UICodeEntity u where u.parentId = :parentId")
  Optional<Integer> operate(String parentId);
}
