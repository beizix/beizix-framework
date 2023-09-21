package org.beizix.core.feature.uicode.persistence.dao.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.beizix.core.feature.uicode.persistence.model.UICodeEntity;

public interface UICodeSortRepo
    extends JpaRepository<UICodeEntity, String>, JpaSpecificationExecutor<UICodeEntity> {
  @Modifying
  @Query("update UICodeEntity m set m.orderNo = :orderNo where m.id = :id")
  void operate(@Param("id") String id, @Param("orderNo") Integer orderNo);
}
