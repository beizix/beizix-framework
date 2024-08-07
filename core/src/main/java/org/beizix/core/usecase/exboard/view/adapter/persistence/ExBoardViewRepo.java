package org.beizix.core.usecase.exboard.view.adapter.persistence;

import java.util.Optional;
import org.beizix.core.config.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExBoardViewRepo extends JpaRepository<ExBoard, Long> {
  @Override
  @EntityGraph(value = "fetch_attachments")
  Optional<ExBoard> findById(Long aLong);
}
