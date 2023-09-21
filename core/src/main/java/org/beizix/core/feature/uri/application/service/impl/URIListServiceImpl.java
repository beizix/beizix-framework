package org.beizix.core.feature.uri.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URIListService;
import org.beizix.core.feature.uri.persistence.dao.URIListDao;

import java.util.List;

@Service
@RequiredArgsConstructor
class URIListServiceImpl implements URIListService {
  private final URIListDao uriListDao;

  @Cacheable("URIItemsByAppTypeCache")
  @Override
  public List<URI> operate(AppType appType) {
    return uriListDao.operate(appType);
  }
}
