package app.module.admin.config.adapter.persistence.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/** Spring Security - Remember me 기능을 위한 엔티티 */
@Getter
@Setter
@Entity
public class PersistentLogins {
  @Id private String series;
  private String username;
  private String token;
  private Date lastUsed;
}
