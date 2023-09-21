package org.beizix.core.feature.uri.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.exception.NonRemovableItemException;
import org.beizix.core.feature.uri.application.service.URIRemoveService;
import org.beizix.core.feature.uri.application.service.URIViewService;
import org.beizix.core.feature.uri.persistence.dao.URIRemoveDao;
import org.beizix.utility.common.MessageUtil;

@Service
@RequiredArgsConstructor
class URIRemoveServiceImpl implements URIRemoveService {
  private final URIRemoveDao uriRemoveDao;
  private final URIViewService uriViewService;
  private final MessageUtil messageUtil;

  @CacheEvict(
      value = {"URITopItemCache", "URIItemsByAppTypeCache"},
      allEntries = true)
  @Override
  public void operate(AppType appType, String id) {
    uriViewService
        .operate(appType, id)
        .filter(uri -> uri.getParentId() == null)
        .ifPresent(
            uri -> {
              throw new NonRemovableItemException(
                  messageUtil.getMessage("uri.remove.prevent.root"));
            });

    uriRemoveDao.operate(id);
  }
}
