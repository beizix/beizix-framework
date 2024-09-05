package app.module.core.usecase.file.createFile.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.core.usecase.file.createFile.ports.application.domain.CreateFile;
import app.module.core.usecase.file.createFile.ports.application.domain.CreateFileCmd;
import app.module.core.usecase.file.createFile.ports.CreateFilePortIn;
import app.module.core.usecase.file.createFile.ports.CreateFilePortOut;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateFileService implements CreateFilePortIn {
  private final CreateFilePortOut portOut;

  @Override
  public Optional<CreateFile> operate(CreateFileCmd command) {
    return portOut.operate(command);
  }
}