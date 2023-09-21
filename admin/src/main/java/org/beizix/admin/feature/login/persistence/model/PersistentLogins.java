package org.beizix.admin.feature.login.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Spring Security - Remember me 기능을 위한 엔티티
 */
@Getter
@Setter
@Entity
public class PersistentLogins {
  @Id
  private String series;
  private String username;
  private String token;
  private Date lastUsed;
}
