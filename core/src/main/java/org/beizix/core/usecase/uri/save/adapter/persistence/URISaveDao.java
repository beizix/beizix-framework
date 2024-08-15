package org.beizix.core.usecase.uri.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.uri.save.application.domain.URISaveCommand;
import org.beizix.core.usecase.uri.save.application.port.out.URISavePortOut;
import org.beizix.core.config.adapter.persistence.entity.URI;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class URISaveDao implements URISavePortOut {
  private final URISaveRepo uriRepo;

  @Override
  public String connect(URISaveCommand saveCommand) {
    URI createdItem =
        uriRepo.save(
            new URI(
                saveCommand.getId(),
                saveCommand.getAppType(),
                saveCommand.getParentId(),
                saveCommand.getOrderNo(),
                saveCommand.getText(),
                saveCommand.getUri(),
                saveCommand.getShowOnNavi(),
                saveCommand.getOgTitle(),
                saveCommand.getOgDesc(),
                saveCommand.getOgKeywords(),
                saveCommand.getOgImage(),
                null,
                saveCommand.getRoles()));
    return createdItem.getId();
  }
}
