package app.module.core.usecase.uri.list.adapter.persistence;

import java.util.List;
import app.module.core.config.adapter.persistence.entity.URI;
import app.module.core.config.application.enums.AppType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URIListRepo extends JpaRepository<URI, String> {

  List<URI> findAllByAppType(AppType appType);
}
