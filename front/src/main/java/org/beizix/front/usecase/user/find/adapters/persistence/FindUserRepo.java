package org.beizix.front.usecase.user.find.adapters.persistence;

import org.beizix.core.config.adapter.persistence.entity.FrontUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FindUserRepo extends JpaRepository<FrontUser, String> {}
