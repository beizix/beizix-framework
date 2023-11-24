package org.beizix.admin.usecase.uicode.view.adapter.persistence;

import org.beizix.core.adapter.persistence.uicode.model.UICode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UICodeViewRepo extends JpaRepository<UICode, String> {}
