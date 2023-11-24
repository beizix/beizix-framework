package org.beizix.admin.usecase.uicode.remove.adapter.persistence;

import org.beizix.core.adapter.persistence.uicode.model.UICode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UICodeRemoveRepo
    extends JpaRepository<UICode, String> {

}
