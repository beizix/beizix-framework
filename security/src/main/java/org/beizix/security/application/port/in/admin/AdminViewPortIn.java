package org.beizix.security.application.port.in.admin;

import java.util.Optional;
import org.beizix.security.application.domain.admin.model.view.AdminViewOutput;

public interface AdminViewPortIn {
  Optional<AdminViewOutput> connect(String adminId);
}
