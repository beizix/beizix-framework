package org.beizix.core.feature.exboard.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.core.feature.fileUpload.application.model.FileUploadInfo;
import org.beizix.core.feature.fileUpload.persistence.model.FileUploadInfoEmbeddable;

import javax.persistence.*;

import org.hibernate.annotations.Comment;

@Entity
@Table(name = "example_board_attachment")
@org.hibernate.annotations.Table(appliesTo = "example_board_attachment", comment = "다건 첨부파일 테이블")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExBoardAttachmentEntity {
  @Id
  @GeneratedValue
  @Comment("다건 첨부 파일 아이디")
  private Long id;

  @Embedded private FileUploadInfoEmbeddable fileUploadInfo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="exBoardId")
  @Comment("예제 게시판 아이디")
  private ExBoardEntity exBoard;
}
