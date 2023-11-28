package org.beizix.admin.config.adapter.persistence.entity;

import javax.persistence.*;
import lombok.*;
import org.beizix.core.config.adapter.persistence.component.AuditEntity;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_with_privilege")
public class RoleWithPrivilege extends AuditEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id")
  @Comment(value = "권한")
  private Role role;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "privilege_id")
  @Comment(value = "권한")
  private Privilege privilege;
}
