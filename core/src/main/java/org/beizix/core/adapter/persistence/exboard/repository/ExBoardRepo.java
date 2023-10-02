package org.beizix.core.adapter.persistence.exboard.repository;

import java.util.Optional;
import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExBoardRepo
    extends JpaRepository<ExBoard, Long>, JpaSpecificationExecutor<ExBoard> {
  @Override
  @EntityGraph(value = "eg_exboard_view")
  Optional<ExBoard> findById(Long aLong);
}
