package org.beizix.security.adapter.persistence.role.model;

import java.util.Set;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Comment;
import org.beizix.core.adapter.persistence.common.model.BaseEntity;
import org.beizix.security.adapter.persistence.role_privilege.model.RoleWithPrivilege;

@Entity
@Table(name = "role")
@org.hibernate.annotations.Table(appliesTo = "role", comment = "어드민 역할 테이블")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
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
