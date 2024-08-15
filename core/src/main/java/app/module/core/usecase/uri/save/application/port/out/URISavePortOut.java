package app.module.core.usecase.uri.save.application.port.out;


import app.module.core.usecase.uri.save.application.domain.URISaveCommand;

public interface URISavePortOut {
  String connect(URISaveCommand uri);
}
