package org.beizix.core.adapter.persistence.exboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.beizix.core.adapter.persistence.exboard.model.ExBoardEntity;

public interface ExBoardMaxOrderNoRepo
    extends JpaRepository<ExBoardEntity, Long>, JpaSpecificationExecutor<ExBoardEntity> {

  @Query("select max(e.orderNo) from ExBoardEntity e")
  Optional<Integer> operate();
}
