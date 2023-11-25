package org.beizix.admin.usecase.uri.save.application.port.out;


import org.beizix.core.application.domain.uri.model.save.URIInput;

public interface URISavePortOut {
  String connect(URIInput uri);
}
