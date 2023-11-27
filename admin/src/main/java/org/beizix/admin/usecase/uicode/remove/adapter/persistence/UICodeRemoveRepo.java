package org.beizix.admin.usecase.uicode.remove.adapter.persistence;

import org.beizix.core.configuration.adapter.persistence.entity.UICode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UICodeRemoveRepo
    extends JpaRepository<UICode, String> {

}
