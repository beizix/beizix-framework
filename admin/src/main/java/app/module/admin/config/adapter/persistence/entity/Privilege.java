package app.module.admin.config.adapter.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;
import app.module.core.config.adapter.persistence.component.AuditEntity;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "privilege")
@org.hibernate.annotations.Table(appliesTo = "privilege", comment = "어드민 권한 테이블")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Privilege extends AuditEntity {
  @Id
  @Comment("관리자 권한")
  private String id;

  @Column
  @Comment("설명")
  private String description;

  @Column
  @Comment("정렬순서")
  private Integer orderNo;

  public Privilege(String id) {
    this.id = id;
  }
}
