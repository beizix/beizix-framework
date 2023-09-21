package org.beizix.security.application.port.out.admin;

import java.util.Optional;
import org.beizix.security.application.domain.admin.model.view.AdminViewResp;

public interface AdminViewDao {
  Optional<AdminViewResp> operate(String id);
}
