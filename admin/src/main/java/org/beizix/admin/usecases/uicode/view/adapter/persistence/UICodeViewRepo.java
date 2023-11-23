package org.beizix.admin.usecases.uicode.view.adapter.persistence;

import org.beizix.core.adapter.persistence.uicode.model.UICode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UICodeViewRepo extends JpaRepository<UICode, String> {}
