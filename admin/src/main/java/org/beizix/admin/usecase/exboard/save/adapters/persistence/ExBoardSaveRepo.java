package org.beizix.admin.usecase.exboard.save.adapters.persistence;

import org.beizix.core.config.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExBoardSaveRepo extends JpaRepository<ExBoard, Long> {}
