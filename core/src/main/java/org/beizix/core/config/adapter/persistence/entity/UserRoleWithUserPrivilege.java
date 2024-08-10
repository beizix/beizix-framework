package org.beizix.core.config.adapter.persistence.entity;

import javax.persistence.*;
import lombok.*;
import org.beizix.core.config.adapter.persistence.component.AuditEntity;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user_role_with_user_privilege")
public class UserRoleWithUserPrivilege extends AuditEntity {
  protected UserRoleWithUserPrivilege() {}

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_role_id")
  @Comment(value = "사용자 역할 참조 칼럼")
  private UserRole userRole;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_privilege_id")
  @Comment(value = "사용자 권한 참조 칼럼")
  private UserPrivilege userPrivilege;
}
