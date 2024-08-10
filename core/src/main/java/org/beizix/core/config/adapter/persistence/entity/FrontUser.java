package org.beizix.core.config.adapter.persistence.entity;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.config.adapter.persistence.component.AuditEntity;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Comment;

@Entity
@org.hibernate.annotations.Table(appliesTo = "front_user", comment = "사용자 테이블")
@Getter
@Setter
@AllArgsConstructor
public class FrontUser extends AuditEntity {
  protected FrontUser() {}

  @Id
  @Comment("계정 아이디")
  private String id;

  @Column
  @Comment("패스워드")
  private String password;

  @Column(nullable = false)
  @Comment("이메일")
  private String email;

  @Column
  @Comment("비활성화 여부")
  Boolean accountDisabled;

  @Column
  @Comment("로그인 실패횟수")
  Integer loginFailCnt;

  @Column
  @Comment("잠금 여부")
  Boolean accountLocked;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = UserWithUserRole_.FRONT_USER,
      cascade = {CascadeType.REMOVE})
  @BatchSize(size = 100)
  private Set<UserWithUserRole> withUserRoles = new LinkedHashSet<>();
}
