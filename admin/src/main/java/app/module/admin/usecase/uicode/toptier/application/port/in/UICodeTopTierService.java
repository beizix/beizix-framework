package app.module.admin.usecase.uicode.toptier.application.port.in;

import app.module.admin.usecase.uicode.toptier.application.domain.UICodeTopTier;
import app.module.admin.usecase.uicode.toptier.application.port.out.UICodeTopTierPortOut;
import lombok.RequiredArgsConstructor;
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
