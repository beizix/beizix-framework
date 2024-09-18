package app.module.admin.config.adapter.persistence.initdata.helper;

import app.module.core.config.adapter.persistence.entity.URI;
import app.module.core.config.application.enums.AppType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoveAllAdminURIRepo extends JpaRepository<URI, String> {
  void deleteAllByAppType(AppType appType);
}
