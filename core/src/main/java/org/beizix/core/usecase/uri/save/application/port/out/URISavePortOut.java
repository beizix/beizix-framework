package org.beizix.core.usecase.uri.save.application.port.out;


import org.beizix.core.usecase.uri.save.application.domain.URISaveCommand;

public interface URISavePortOut {
  String connect(URISaveCommand uri);
}
