package org.beizix.core.adapter.persistence.operationlog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.enums.OperationLogType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation_log")
@org.hibernate.annotations.Table(appliesTo = "operation_log", comment = "관리자 로그 테이블")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationLog {
  @Id
  @GeneratedValue
  @Comment("로그 아이디")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Comment("앱 타입")
  private AppType appType;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Comment("운영 타입")
  private OperationLogType operationLogType;

  @Column(nullable = false)
  @Comment("수행자")
  private String operatorId;

  @Column
  @Comment("피수행자")
  private String targetId;

  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  @Comment("수행일")
  private LocalDateTime operatedAt;

  @Column
  @Comment("아이피")
  private String ip;

  @Column(length = 1024)
  @Comment("작업 내용")
  private String taskDesc;
}
