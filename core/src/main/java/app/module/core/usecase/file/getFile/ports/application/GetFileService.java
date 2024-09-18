package app.module.core.usecase.file.getFile.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.core.usecase.file.getFile.ports.application.domain.GetFile;
import app.module.core.usecase.file.getFile.ports.application.domain.GetFileCmd;
import app.module.core.usecase.file.getFile.ports.GetFilePortIn;
import app.module.core.usecase.file.getFile.ports.GetFilePortOut;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetFileService implements GetFilePortIn {
  private final GetFilePortOut portOut;

  @Override
  public Optional<GetFile> operate(GetFileCmd command) {
    return portOut.operate(command);
  }
}