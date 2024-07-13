package org.beizix.admin.usecase.exboard.remove.adapters.persistence;

import org.beizix.core.config.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExBoardRemoveRepo extends JpaRepository<ExBoard, Long> {}
