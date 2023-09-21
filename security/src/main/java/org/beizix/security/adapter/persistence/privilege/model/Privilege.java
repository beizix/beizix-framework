package org.beizix.security.adapter.persistence.privilege.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.beizix.core.common.entity.BaseEntity;

@Entity
@Table(name = "privilege")
@org.hibernate.annotations.Table(appliesTo = "privilege", comment = "어드민 권한 테이블")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Privilege extends BaseEntity {
  @Id
  @Comment("관리자 권한")
  private String id;
}
