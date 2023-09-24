package org.beizix.core.adapter.persistence.exboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.beizix.core.adapter.persistence.exboard.model.ExBoardEntity;

public interface ExBoardRepo
    extends JpaRepository<ExBoardEntity, Long>, JpaSpecificationExecutor<ExBoardEntity> {}
