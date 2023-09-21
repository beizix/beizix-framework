package org.beizix.core.feature.exboard.persistence.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.beizix.core.feature.exboard.persistence.model.ExBoardAttachmentEntity;

public interface ExBoardAttachmentRepo
    extends JpaRepository<ExBoardAttachmentEntity, Long>,
        JpaSpecificationExecutor<ExBoardAttachmentEntity> {}
