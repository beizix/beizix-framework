package app.module.admin.usecase.exboard.save.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExBoardSaveRepo extends JpaRepository<ExBoard, Long> {}
