package org.beizix.core.config.adapter.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
@AllArgsConstructor
public class UserRole {
  protected UserRole() {}

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
      fetch = FetchType.LAZY,
      mappedBy = UserRoleWithUserPrivilege_.USER_ROLE,
      cascade = {CascadeType.REMOVE})
  @BatchSize(size = 100)
  private Set<UserRoleWithUserPrivilege> withUserPrivileges = new LinkedHashSet<>();
}