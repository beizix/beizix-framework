package org.beizix.admin.usecase.admin.view.application.port.out;

import java.util.Optional;
import org.beizix.admin.usecase.admin.view.application.domain.AdminView;

public interface AdminViewPortOut {
  Optional<AdminView> connect(String id);
}
