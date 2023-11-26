package org.beizix.core.usecase.exboard.view.adapter.persistence;

import org.beizix.core.configuration.adapter.persistence.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExBoardViewRepo extends JpaRepository<ExBoard, Long> {}
