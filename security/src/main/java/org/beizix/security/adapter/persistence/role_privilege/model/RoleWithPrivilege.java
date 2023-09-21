package org.beizix.security.adapter.persistence.role_privilege.model;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.beizix.core.common.entity.BaseEntity;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.beizix.security.adapter.persistence.role.model.Role;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_with_privilege")
public class RoleWithPrivilege extends BaseEntity {
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
