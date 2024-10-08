package app.module.admin.config.adapter.persistence.entity;

import java.util.Set;
import javax.persistence.*;
import lombok.*;
import app.module.core.config.adapter.persistence.component.AuditEntity;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "role")
@org.hibernate.annotations.Table(appliesTo = "role", comment = "어드민 역할 테이블")
@Getter
@Setter
@AllArgsConstructor
public class Role extends AuditEntity {
  protected Role() {}

  public Role(String id) {
    this.id = id;
  }

  @Id
  @Comment("관리자 역할")
  private String id;

  @Column
  @Comment("설명")
  private String description;

  @Column
  @Comment("정렬순서")
  private Integer orderNo;

  @OneToMany(
      mappedBy = "role",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @BatchSize(size = 100)
  private Set<RoleWithPrivilege> withPrivileges;
}
