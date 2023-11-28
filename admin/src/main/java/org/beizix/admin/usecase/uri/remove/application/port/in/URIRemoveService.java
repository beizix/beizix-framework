package org.beizix.admin.usecase.uri.remove.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uri.remove.application.port.out.URIRemovePortOut;
import org.beizix.core.config.application.enums.AppType;
import org.beizix.core.config.application.exception.NonRemovableItemException;
import org.beizix.core.usecase.uri.view.application.port.in.URIViewPortIn;
import org.beizix.core.config.application.util.MessageUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

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
