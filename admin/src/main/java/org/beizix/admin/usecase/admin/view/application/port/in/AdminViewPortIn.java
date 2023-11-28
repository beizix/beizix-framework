package org.beizix.admin.usecase.admin.view.application.port.in;

import java.util.Optional;
import org.beizix.admin.usecase.admin.view.application.domain.AdminView;

public interface AdminViewPortIn {
  Optional<AdminView> connect(String adminId);
}
