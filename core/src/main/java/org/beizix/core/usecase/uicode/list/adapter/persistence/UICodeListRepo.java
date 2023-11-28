package org.beizix.core.usecase.uicode.list.adapter.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.beizix.core.config.adapter.persistence.entity.UICode;

public interface UICodeListRepo
    extends JpaRepository<UICode, String>, JpaSpecificationExecutor<UICode> {
  @Query("select e from UICode e where e.parentId = :parentId")
  List<UICode> operate(String parentId);
}
