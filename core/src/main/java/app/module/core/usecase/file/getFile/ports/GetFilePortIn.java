package app.module.core.usecase.file.getFile.ports;

import java.util.Optional;
import app.module.core.usecase.file.getFile.ports.application.domain.GetFile;
import app.module.core.usecase.file.getFile.ports.application.domain.GetFileCmd;

public interface GetFilePortIn {
  Optional<GetFile> operate(GetFileCmd command);
}