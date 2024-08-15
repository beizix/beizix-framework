package app.module.admin.usecase.uicode.toptier.adapter.persistence;

import java.util.stream.Collectors;
import javax.transaction.Transactional;

import app.module.admin.usecase.uicode.toptier.application.domain.UICodeTopTier;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import app.module.admin.usecase.uicode.toptier.application.port.out.UICodeTopTierPortOut;
import app.module.core.config.adapter.persistence.entity.UICode;
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
