package org.beizix.core.config.adapter.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.beizix.core.config.adapter.persistence.component.AuditEntity;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user_role")
@org.hibernate.annotations.Table(appliesTo = "user_role", comment = "사용자 역할 테이블")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends AuditEntity {
  public UserRole(String id) {
    this.id = id;
  }

  @Id
  @Comment("사용자 역할 아이디")
  private String id;

  @Column
  @Comment("설명")
  private String description;

  @Column
  @Comment("정렬순서")
  private Integer orderNo;

  @OneToMany(
      fetch = FetchType.EAGER,
      mappedBy = UserRoleWithUserPrivilege_.USER_ROLE,
      cascade = {CascadeType.MERGE, CascadeType.REMOVE})
  @BatchSize(size = 100)
  private Set<UserRoleWithUserPrivilege> withUserPrivileges = new LinkedHashSet<>();
}
