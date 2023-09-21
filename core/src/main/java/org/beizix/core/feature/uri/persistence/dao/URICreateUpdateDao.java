package org.beizix.core.feature.uri.persistence.dao;

import org.beizix.core.feature.uri.application.model.URI;

public interface URICreateUpdateDao {
  URI operate(URI uri);
}
