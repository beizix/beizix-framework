package org.beizix.core.feature.uri.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.feature.uri.application.model.URISort;
import org.beizix.core.feature.uri.application.service.URISortService;
import org.beizix.core.feature.uri.persistence.dao.URISortDao;

import java.util.List;

@Service
@RequiredArgsConstructor
class URISortServiceImpl implements URISortService {
  private final URISortDao uriSortDao;

  @CacheEvict(
      value = {"URITopItemCache", "URIItemsByAppTypeCache"},
      allEntries = true)
  @Override
  public void operate(List<URISort> uris) {
    uriSortDao.operate(uris);
  }
}
