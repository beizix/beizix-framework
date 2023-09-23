package org.beizix.security.adapter.persistence.admin_role.model;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.beizix.core.adapter.persistence.common.model.BaseEntity;
import org.beizix.security.adapter.persistence.role.model.Role;
import org.beizix.security.adapter.persistence.admin.model.Admin;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_with_role")
public class AdminWithRole extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @Comment(value = "어드민 아아디")
  @JoinColumn(name="admin_id", nullable = false)
  private Admin admin;

  @ManyToOne(fetch = FetchType.EAGER)
  @Comment(value = "권한")
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;
}
