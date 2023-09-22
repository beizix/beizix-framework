package org.beizix.security.application.port.in.role;

import java.util.List;
import org.beizix.security.application.domain.role.model.list.RoleListOutput;

public interface RoleListPortIn {
  List<RoleListOutput> connect();
}
