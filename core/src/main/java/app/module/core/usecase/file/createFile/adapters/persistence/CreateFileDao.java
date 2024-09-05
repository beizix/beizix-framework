package app.module.core.usecase.file.createFile.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.UploadFile;
import lombok.RequiredArgsConstructor;
import app.module.core.usecase.file.createFile.ports.application.domain.CreateFile;
import app.module.core.usecase.file.createFile.ports.application.domain.CreateFileCmd;
import app.module.core.usecase.file.createFile.ports.CreateFilePortOut;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class CreateFileDao implements CreateFilePortOut {
  private final CreateFileRepo createFileRepo;

  @Override
  public Optional<CreateFile> operate(CreateFileCmd command) {
    UploadFile uploadFile =
        createFileRepo.save(
            new UploadFile(
                null,
                command.getType(),
                command.getPath(),
                command.getName(),
                command.getOriginName(),
                command.getFileLength()));

    return Optional.of(
        new CreateFile(
            uploadFile.getId(),
            uploadFile.getType(),
            uploadFile.getPath(),
            uploadFile.getName(),
            uploadFile.getOriginName(),
            uploadFile.getFileLength()));
  }
}
