package org.beizix.core.adapter.persistence.exboard.repository;

import org.beizix.core.configuration.adapter.persistence.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExBoardSortRepo
    extends JpaRepository<ExBoard, Long>, JpaSpecificationExecutor<ExBoard> {

  @Modifying
  @Query("update ExBoard e set e.orderNo = :orderNo where e.id = :id")
  void operate(@Param("id") Long id, @Param("orderNo") Integer orderNo);
}
