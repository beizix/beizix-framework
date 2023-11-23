package org.beizix.admin.usecases.uicode.toptier.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecases.uicode.toptier.application.port.out.UICodeTopTierPortOut;
import org.beizix.admin.usecases.uicode.toptier.domain.UICodeTopTier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UICodeTopTierService implements UICodeTopTierPortIn {
  private final UICodeTopTierPortOut portOut;

  @Override
  public UICodeTopTier connect() {
    return portOut.connect();
  }
}
