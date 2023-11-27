package org.beizix.security.adapter.persistence.admin.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.admin.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {


}
