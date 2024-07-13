package org.beizix.admin.usecase.admin.view.ports;

import java.util.Optional;
import org.beizix.admin.usecase.admin.view.ports.application.domain.AdminView;

public interface AdminViewPortIn {
  Optional<AdminView> connect(String adminId);
}
