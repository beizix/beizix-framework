package app.module.admin.usecase.uri.remove.adapter.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uri.remove.application.port.out.URIRemovePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class URIRemoveDao implements URIRemovePortOut {
  private final URIRemoveRepo uriRepo;

  @Override
  public void connect(String id) {
    uriRepo.deleteById(id);
  }
}
