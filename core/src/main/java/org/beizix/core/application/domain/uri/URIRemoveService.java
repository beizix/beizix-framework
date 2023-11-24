package org.beizix.core.application.domain.uri;

import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.uri.view.application.port.in.URIViewPortIn;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.exception.NonRemovableItemException;
import org.beizix.core.application.port.in.uri.URIRemovePortIn;
import org.beizix.core.application.port.out.uri.URIRemovePortOut;
import org.beizix.utility.common.MessageUtil;

@Service
@RequiredArgsConstructor
class URIRemoveService implements URIRemovePortIn {
  private final URIRemovePortOut uriRemovePortOut;
  private final URIViewPortIn uriViewPortIn;
  private final MessageUtil messageUtil;

  @CacheEvict(
      value = {"URIItemsByAppTypeCache", "URITopTierCache"},
      allEntries = true)
  @Override
  public void connect(AppType appType, String id) {
    uriViewPortIn
        .connect(appType, id)
        .filter(uri -> uri.getParentId() == null)
        .ifPresent(
            uri -> {
              throw new NonRemovableItemException(
                  messageUtil.getMessage("uri.remove.prevent.root"));
            });

    uriRemovePortOut.connect(id);
  }
}
