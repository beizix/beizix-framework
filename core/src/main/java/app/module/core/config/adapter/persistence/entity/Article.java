package app.module.core.config.adapter.persistence.entity;

import app.module.core.config.adapter.persistence.component.AuditEntity;
import app.module.core.config.adapter.persistence.component.FileUploadInfoEmbeddable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.mapping.Collection;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
@org.hibernate.annotations.Table(appliesTo = "article", comment = "예제게시판")
public class Article extends AuditEntity {
  @Id
  @GeneratedValue
  @Comment("예제 게시판 아이디")
  private Long id;

  @Column
  @Comment("제목")
  private String title;

  @Lob
  @Comment("내용")
  private String content;

  @Column
  @Comment("공개여부")
  private Boolean visible;
  @Column
  @Comment("게시 시작일")
  private LocalDateTime startDate;

  @Column
  @Comment("게시 종료일")
  private LocalDateTime endDate;

  @Column
  @Comment("게시글 정렬순서")
  Integer orderNo;

  @ManyToMany
  @JoinTable(name = "article_with_upload_file")
  @BatchSize(size = 100)
  private List<UploadFile> uploadFiles = Collections.emptyList();
}
