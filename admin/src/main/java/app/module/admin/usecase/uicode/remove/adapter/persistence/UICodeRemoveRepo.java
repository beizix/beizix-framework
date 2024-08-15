package app.module.admin.usecase.uicode.remove.adapter.persistence;

import app.module.core.config.adapter.persistence.entity.UICode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UICodeRemoveRepo
    extends JpaRepository<UICode, String> {

}
