package org.beizix.core.adapter.persistence.exboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.beizix.core.adapter.persistence.exboard.model.ExBoardAttachmentEntity;

public interface ExBoardAttachmentRepo
    extends JpaRepository<ExBoardAttachmentEntity, Long>,
        JpaSpecificationExecutor<ExBoardAttachmentEntity> {}
