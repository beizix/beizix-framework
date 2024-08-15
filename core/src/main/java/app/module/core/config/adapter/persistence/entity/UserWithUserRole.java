package app.module.core.config.adapter.persistence.entity;

import javax.persistence.*;
import lombok.*;
import app.module.core.config.adapter.persistence.component.AuditEntity;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user_with_user_role")
public class UserWithUserRole extends AuditEntity {
  protected UserWithUserRole() {}

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @Comment(value = "어드민 아아디")
  @JoinColumn(name = "user_id", nullable = false)
  private FrontUser frontUser;

  @ManyToOne(fetch = FetchType.EAGER)
  @Comment(value = "권한")
  @JoinColumn(name = "user_role_id", nullable = false)
  private UserRole role;
}
