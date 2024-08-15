package app.module.admin.usecase.uicode.sort.adapter.persistence;

import app.module.core.config.adapter.persistence.entity.UICode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UICodeSortRepo extends JpaRepository<UICode, String> {
  @Modifying
  @Query("update UICode m set m.orderNo = :orderNo where m.id = :id")
  void operate(@Param("id") String id, @Param("orderNo") Integer orderNo);
}
