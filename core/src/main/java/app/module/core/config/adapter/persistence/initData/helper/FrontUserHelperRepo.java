package app.module.core.config.adapter.persistence.initData.helper;

import app.module.core.config.adapter.persistence.entity.FrontUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrontUserHelperRepo extends JpaRepository<FrontUser, String> {}
