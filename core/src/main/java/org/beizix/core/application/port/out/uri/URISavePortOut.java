package org.beizix.core.application.port.out.uri;


import org.beizix.core.application.domain.uri.model.save.URIInput;

public interface URISavePortOut {
  String connect(URIInput uri);
}
