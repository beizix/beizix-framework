package org.beizix.admin.usecase.exboard.save.adapter.persistence;

import java.util.Optional;

import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ExBoardMaxOrderNoRepo
    extends JpaRepository<ExBoard, Long>, JpaSpecificationExecutor<ExBoard> {

  @Query("select max(e.orderNo) from ExBoard e")
  Optional<Integer> operate();
}
