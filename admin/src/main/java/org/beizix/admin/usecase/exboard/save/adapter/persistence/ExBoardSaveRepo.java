package org.beizix.admin.usecase.exboard.save.adapter.persistence;

import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExBoardSaveRepo extends JpaRepository<ExBoard, Long> {}
