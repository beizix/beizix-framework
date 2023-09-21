package org.beizix.core.feature.exboard.persistence.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.beizix.core.feature.exboard.persistence.model.ExBoardEntity;

interface ExBoardSortRepo
    extends JpaRepository<ExBoardEntity, Long>, JpaSpecificationExecutor<ExBoardEntity> {

  @Modifying
  @Query("update ExBoardEntity e set e.orderNo = :orderNo where e.id = :id")
  void operate(@Param("id") Long id, @Param("orderNo") Integer orderNo);
}
