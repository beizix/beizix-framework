package org.beizix.core.usecase.uri.list.adapter.persistence;

import java.util.List;
import org.beizix.core.configuration.adapter.persistence.entity.URI;
import org.beizix.core.configuration.application.enums.AppType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URIListRepo extends JpaRepository<URI, String> {

  List<URI> findAllByAppType(AppType appType);
}
