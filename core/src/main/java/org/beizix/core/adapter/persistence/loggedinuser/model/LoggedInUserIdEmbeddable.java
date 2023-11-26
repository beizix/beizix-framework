package org.beizix.core.adapter.persistence.loggedinuser.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;
import org.beizix.core.configuration.application.enums.AppType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserIdEmbeddable implements Serializable {
  @Column
  @Comment("앱 타입")
  @Enumerated(EnumType.STRING)
  private AppType appType;

  @Column
  @Comment("로그인 아이디")
  private String id;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    LoggedInUserIdEmbeddable that = (LoggedInUserIdEmbeddable) o;

    if (appType != that.appType) return false;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(appType);
    result = 31 * result + (Objects.hashCode(id));
    return result;
  }
}
