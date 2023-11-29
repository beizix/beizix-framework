package org.beizix.core.config.adapter.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.*;
import org.beizix.core.config.application.enums.AppType;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;

/** 로그인 기록 테이블 LoggedInUser 의 고유키 객체. AppType 과 id 로 구성된 합성키다. */
@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserEmbeddable implements Serializable {
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
    LoggedInUserEmbeddable that = (LoggedInUserEmbeddable) o;

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
