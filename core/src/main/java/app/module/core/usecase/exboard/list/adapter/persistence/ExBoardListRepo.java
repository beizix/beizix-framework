package app.module.core.usecase.exboard.list.adapter.persistence;

import app.module.core.config.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExBoardListRepo
    extends JpaRepository<ExBoard, Long>, JpaSpecificationExecutor<ExBoard> {}
