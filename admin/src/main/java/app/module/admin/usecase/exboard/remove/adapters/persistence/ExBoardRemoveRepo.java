package app.module.admin.usecase.exboard.remove.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExBoardRemoveRepo extends JpaRepository<ExBoard, Long> {}
