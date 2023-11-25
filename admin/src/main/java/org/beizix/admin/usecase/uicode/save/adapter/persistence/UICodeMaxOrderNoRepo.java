package org.beizix.admin.usecase.uicode.save.adapter.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.beizix.core.adapter.persistence.uicode.model.UICode;

public interface UICodeMaxOrderNoRepo
    extends JpaRepository<UICode, String>, JpaSpecificationExecutor<UICode> {
  @Query("select max(u.orderNo) from UICode u where u.parentId = :parentId")
  Optional<Integer> operate(String parentId);
}
