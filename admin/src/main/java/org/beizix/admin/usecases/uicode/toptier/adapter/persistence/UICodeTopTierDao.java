package org.beizix.admin.usecases.uicode.toptier.adapter.persistence;

import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.beizix.admin.usecases.uicode.toptier.application.port.out.UICodeTopTierPortOut;
import org.beizix.admin.usecases.uicode.toptier.domain.UICodeTopTier;
import org.beizix.core.adapter.persistence.uicode.model.UICode;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class UICodeTopTierDao implements UICodeTopTierPortOut {
  private final UICodeTopTierRepo uiCodeTopTierRepo;

  @Override
  @Transactional
  public UICodeTopTier connect() {
    UICode topTier = uiCodeTopTierRepo.findByParentIdIsNull();
    return recursiveMapping(topTier);
  }

  private UICodeTopTier recursiveMapping(UICode topTier) {
    return new UICodeTopTier(
        topTier.getId(),
        topTier.getCodeText(),
        topTier.getOrderNo(),
        CollectionUtils.emptyIfNull(topTier.getChildren()).stream()
            .map(this::recursiveMapping)
            .collect(Collectors.toList()));
  }
}
