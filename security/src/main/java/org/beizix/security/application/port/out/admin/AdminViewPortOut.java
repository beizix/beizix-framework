package org.beizix.security.application.port.out.admin;

import java.util.Optional;
import org.beizix.security.application.domain.admin.model.view.AdminViewOutput;

public interface AdminViewPortOut {
  Optional<AdminViewOutput> connect(String id);
}
