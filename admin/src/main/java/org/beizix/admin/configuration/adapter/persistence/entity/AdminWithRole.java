package org.beizix.admin.configuration.adapter.persistence.entity;

import javax.persistence.*;
import lombok.*;
import org.beizix.core.configuration.adapter.persistence.component.AuditEntity;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_with_role")
public class AdminWithRole extends AuditEntity {
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
