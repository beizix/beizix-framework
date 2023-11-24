package org.beizix.core.usecase.uri.list.adapter.persistence;

import java.util.List;
import org.beizix.core.adapter.persistence.uri.model.URI;
import org.beizix.core.config.enums.AppType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URIListRepo extends JpaRepository<URI, String> {

  List<URI> findAllByAppType(AppType appType);
}
