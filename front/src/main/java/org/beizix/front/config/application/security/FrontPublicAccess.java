package org.beizix.front.config.application.security;

import java.util.*;
import java.util.stream.Collectors;

public class FrontPublicAccess {
  private static final FrontPublicAccess INSTANCE = new FrontPublicAccess();

  private List<String> publicAccessURIs = new ArrayList<>();

  private FrontPublicAccess() {}

  public static FrontPublicAccess getInstance() {
    return INSTANCE;
  }

  public void setURIs(List<String> uris) {
    publicAccessURIs.addAll(uris);
  }

  public List<String> getURIs() {
    return publicAccessURIs;
  }
}
