package org.beizix.core.usecase.exboard.list.adapter.persistence;

import org.beizix.core.configuration.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExBoardListRepo
    extends JpaRepository<ExBoard, Long>, JpaSpecificationExecutor<ExBoard> {}
