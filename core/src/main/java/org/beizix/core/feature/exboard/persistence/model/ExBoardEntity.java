package org.beizix.core.feature.exboard.persistence.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.beizix.core.feature.fileUpload.persistence.model.FileUploadInfoEmbeddable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "example_board")
@org.hibernate.annotations.Table(appliesTo = "example_board", comment = "예제게시판 테이블")
public class ExBoardEntity {
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

  @Column(updatable = false, nullable = false)
  @Comment("생성자")
  private String createdBy;

  @Column
  @Comment("수정자")
  private String updatedBy;

  /** 게시글 대표 이미지: 1건 이기에 외부 엔티티가 아닌 @Embedded 로 처리한다. 공개여부 - public */
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "type", column = @Column(name = "repImgType")),
    @AttributeOverride(name = "path", column = @Column(name = "repImgPath")),
    @AttributeOverride(name = "fileLength", column = @Column(name = "repImgSize")),
    @AttributeOverride(name = "name", column = @Column(name = "repImgName")),
    @AttributeOverride(name = "originName", column = @Column(name = "repImgOrginName"))
  })
  private FileUploadInfoEmbeddable representImage;

  @Comment("대표 이미지 대체 텍스트")
  private String repImgAlt;

  /** 비공개 파일: 1건 이기에 외부 엔티티가 아닌 @Embedded 로 처리한다. 공개여부 - private */
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "type", column = @Column(name = "prvAttachType")),
    @AttributeOverride(name = "path", column = @Column(name = "prvAttachPath")),
    @AttributeOverride(name = "fileLength", column = @Column(name = "prvAttachSize")),
    @AttributeOverride(name = "name", column = @Column(name = "prvAttachName")),
    @AttributeOverride(name = "originName", column = @Column(name = "prvAttachOrginName"))
  })
  private FileUploadInfoEmbeddable privateAttachment;

  /** 첨부 파일: 다건 이기에 외부 엔티티로 1:N 관계를 맺는다. 공개여부 - public */
  @OneToMany(mappedBy = "exBoard", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @BatchSize(size = 100)
  @OrderBy("id asc")
  private Set<ExBoardAttachmentEntity> attachments;

  @Column
  @Comment("게시 시작일")
  private LocalDateTime boardStartDate;

  @Column
  @Comment("게시 종료일")
  private LocalDateTime boardEndDate;

  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  @Comment("게시글 등록일")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Comment("게시글 수정일")
  private LocalDateTime updatedAt;

  @Column
  @Comment("게시글 정렬순서")
  Integer orderNo;
}
