package org.beizix.security.application.port.in.admin;

import java.util.Optional;
import org.beizix.security.application.domain.admin.model.view.AdminViewResp;

public interface AdminViewService {
  Optional<AdminViewResp> operate(String adminId);
}
