package app.module.core.usecase.file.getFile.adapters.persistence;

import app.module.core.usecase.file.getFile.ports.GetFilePortOut;
import app.module.core.usecase.file.getFile.ports.application.domain.GetFile;
import app.module.core.usecase.file.getFile.ports.application.domain.GetFileCmd;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class GetFileDao implements GetFilePortOut {
  private final GetFileRepo getFileRepo;

  @Override
  public Optional<GetFile> operate(GetFileCmd command) {
    return getFileRepo
        .findById(command.getId())
        .map(
            uploadFile ->
                new GetFile(
                    uploadFile.getCreatedAt(),
                    uploadFile.getCreatedBy(),
                    uploadFile.getUpdatedAt(),
                    uploadFile.getUpdatedBy(),
                    uploadFile.getId(),
                    uploadFile.getType(),
                    uploadFile.getPath(),
                    uploadFile.getName(),
                    uploadFile.getOriginName(),
                    uploadFile.getFileLength()));
  }
}
