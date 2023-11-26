package org.beizix.core.adapter.persistence.exboard.repository;

import org.beizix.core.configuration.adapter.persistence.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExBoardRepo
    extends JpaRepository<ExBoard, Long>, JpaSpecificationExecutor<ExBoard> {
  
}
