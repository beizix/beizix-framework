package org.beizix.core.config.adapter.persistence.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 동시접속 사용자 체크를 위한 기록용 엔티티
 */
@Table(name = "logged_in_users")
@org.hibernate.annotations.Table(appliesTo = "logged_in_users", comment = "로그인한 사용자 정보")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUser {
  @EmbeddedId private LoggedInUserEmbeddable loggedInUserId;

  @Column
  @Comment("접속 아이피")
  private String clientIP;

  @Column
  @Comment("마지막 로그인 날짜")
  private LocalDateTime lastLoggedInAt;
}
