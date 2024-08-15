package app.module.admin.usecase.uicode.save.adapter.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import app.module.core.config.adapter.persistence.entity.UICode;

public interface UICodeMaxOrderNoRepo
    extends JpaRepository<UICode, String>, JpaSpecificationExecutor<UICode> {
  @Query("select max(u.orderNo) from UICode u where u.parentId = :parentId")
  Optional<Integer> operate(String parentId);
}
