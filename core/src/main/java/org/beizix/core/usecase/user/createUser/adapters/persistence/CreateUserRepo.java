package org.beizix.core.usecase.user.createUser.adapters.persistence;

import org.beizix.core.config.adapter.persistence.entity.FrontUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateUserRepo extends JpaRepository<FrontUser, String> {}
