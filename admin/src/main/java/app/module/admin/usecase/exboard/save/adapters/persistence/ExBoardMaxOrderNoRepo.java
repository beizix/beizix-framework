package app.module.admin.usecase.exboard.save.adapters.persistence;

import java.util.Optional;

import app.module.core.config.adapter.persistence.entity.ExBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ExBoardMaxOrderNoRepo
    extends JpaRepository<ExBoard, Long>, JpaSpecificationExecutor<ExBoard> {

  @Query("select max(e.orderNo) from ExBoard e")
  Optional<Integer> operate();
}
