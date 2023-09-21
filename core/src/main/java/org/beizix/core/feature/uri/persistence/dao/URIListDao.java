package org.beizix.core.feature.uri.persistence.dao;

import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;

import java.util.List;

public interface URIListDao {
  List<URI> operate(AppType appType);
}
