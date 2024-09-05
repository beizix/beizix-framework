package app.module.core.usecase.file.createFile.ports;

import java.util.Optional;
import app.module.core.usecase.file.createFile.ports.application.domain.CreateFile;
import app.module.core.usecase.file.createFile.ports.application.domain.CreateFileCmd;

public interface CreateFilePortOut {
  Optional<CreateFile> operate(CreateFileCmd command);
}