package org.beizix.core.configuration.adapter.persistence.component;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class AuditEntity {
  @CreatedDate
  @Column(updatable = false)
  @Comment(value = "생성일")
  private LocalDateTime createdAt;

  @CreatedBy
  @Column(updatable = false)
  @Comment(value = "생성자 아이디")
  private String createdBy;

  @LastModifiedDate
  @Comment(value = "수정일")
  private LocalDateTime updatedAt;

  @LastModifiedBy
  @Comment(value = "수정자 아이디")
  private String updatedBy;
}
