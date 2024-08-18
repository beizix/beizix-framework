package app.module.admin.usecase.user.list.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.FrontUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GetUserListRepo
    extends JpaRepository<FrontUser, String>, JpaSpecificationExecutor<FrontUser> {

}
