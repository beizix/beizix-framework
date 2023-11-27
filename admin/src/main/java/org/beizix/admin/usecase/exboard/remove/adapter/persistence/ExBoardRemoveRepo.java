package org.beizix.admin.usecase.exboard.remove.adapter.persistence;

import org.beizix.core.configuration.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExBoardRemoveRepo extends JpaRepository<ExBoard, Long> {}
