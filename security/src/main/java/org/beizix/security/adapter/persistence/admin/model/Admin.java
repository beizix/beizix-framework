package org.beizix.security.adapter.persistence.admin.model;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.*;
import org.beizix.core.adapter.persistence.common.model.AuditEntity;
import org.beizix.security.adapter.persistence.admin_role.model.AdminWithRole;
import org.beizix.security.adapter.persistence.admin_role.model.AdminWithRole_;
import org.hibernate.annotations.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = Admin_.EMAIL))
@org.hibernate.annotations.Table(appliesTo = "admin", comment = "어드민 사용자 테이블")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
    name = "view_details_entity_graph",
    attributeNodes = {@NamedAttributeNode(value = Admin_.WITH_ROLES, subgraph = "role_subgraph")},
    subgraphs = {
      @NamedSubgraph(
          name = "role_subgraph",
          attributeNodes = {@NamedAttributeNode(AdminWithRole_.ROLE)})
    })
public class Admin extends AuditEntity {

  public Admin(String id) {
    this.id = id;
  }

  @Id
  @Comment("관리자 계정 아이디")
  private String id;

  @Column
  @Comment("패스워드")
  private String password;

  @Column(nullable = false)
  @Comment("이메일")
  private String email;

  @Column(nullable = false)
  @Comment("비밀번호 수정일")
  private LocalDateTime passwordUpdatedAt;

  @Column
  @Comment("활성화")
  Boolean accountDisabled;

  @Column
  @Comment("로그인 실패횟수")
  Integer loginFailCnt;

  @Column
  @Comment("잠금")
  Boolean accountLocked;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "admin",
      cascade = {CascadeType.REMOVE})
  @BatchSize(size = 100)
  private Set<AdminWithRole> withRoles = new LinkedHashSet<>();
}
