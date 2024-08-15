package app.module.core.usecase.uri.save.adapter.persistence;

import app.module.core.usecase.uri.save.application.port.out.URIMaxOrderNoPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class URIMaxOrderNoDao implements URIMaxOrderNoPortOut {
  private final URIMaxOrderNoRepo uriMaxOrderNoRepo;

  @Override
  public Integer connect(String parentId) {
    return uriMaxOrderNoRepo.getMaxOrderNo(parentId).orElse(-1);
  }
}
