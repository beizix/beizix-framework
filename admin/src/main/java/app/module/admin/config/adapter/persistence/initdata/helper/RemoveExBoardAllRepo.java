package app.module.admin.config.adapter.persistence.initdata.helper;

import app.module.core.config.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoveExBoardAllRepo extends JpaRepository<ExBoard, Long> {}
