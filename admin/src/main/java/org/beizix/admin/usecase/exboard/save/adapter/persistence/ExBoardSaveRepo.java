package org.beizix.admin.usecase.exboard.save.adapter.persistence;

import org.beizix.core.configuration.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExBoardSaveRepo extends JpaRepository<ExBoard, Long> {}
