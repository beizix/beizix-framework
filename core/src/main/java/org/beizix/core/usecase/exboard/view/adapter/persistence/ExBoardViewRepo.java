package org.beizix.core.usecase.exboard.view.adapter.persistence;

import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExBoardViewRepo extends JpaRepository<ExBoard, Long> {}
