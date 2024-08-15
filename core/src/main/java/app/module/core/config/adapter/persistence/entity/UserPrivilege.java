package app.module.core.config.adapter.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;
import app.module.core.config.adapter.persistence.component.AuditEntity;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "user_privilege")
@org.hibernate.annotations.Table(appliesTo = "user_privilege", comment = "사용자 권한 테이블")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPrivilege extends AuditEntity {
  @Id
  @Comment("사용자 권한")
  private String id;

  @Column
  @Comment("설명")
  private String description;

  @Column
  @Comment("정렬순서")
  private Integer orderNo;

  public UserPrivilege(String id) {
    this.id = id;
  }
}
