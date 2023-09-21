package org.beizix.core.feature.uri.persistence.dao;

import org.beizix.core.feature.uri.application.model.URISort;

import java.util.List;

public interface URISortDao {
  void operate(List<URISort> uris);
}
