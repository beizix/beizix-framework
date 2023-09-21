package org.beizix.core.feature.exboard.persistence.dao.impl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.beizix.core.feature.exboard.persistence.model.ExBoardEntity;

interface ExBoardMaxOrderNoRepo
    extends JpaRepository<ExBoardEntity, Long>, JpaSpecificationExecutor<ExBoardEntity> {

  @Query("select max(e.orderNo) from ExBoardEntity e")
  Optional<Integer> operate();
}
