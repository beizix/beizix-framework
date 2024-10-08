package app.module.core.config.adapter.persistence.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import app.module.core.config.adapter.persistence.component.AuditEntity;
import app.module.core.config.adapter.persistence.component.FileUploadInfoEmbeddable;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "example_board_attachment")
@org.hibernate.annotations.Table(appliesTo = "example_board_attachment", comment = "다건 첨부파일 테이블")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExBoardAttachment extends AuditEntity {
  @Id
  @GeneratedValue
  @Comment("다건 첨부 파일 아이디")
  private Long id;

  @Embedded private FileUploadInfoEmbeddable fileUploadOutput;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="exBoardId")
  @Comment("예제 게시판 아이디")
  private ExBoard exBoard;
}
