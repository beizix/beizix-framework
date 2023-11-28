package org.beizix.core.config.adapter.persistence.entity;

import java.util.Set;
import javax.persistence.*;
import lombok.*;
import org.beizix.core.config.application.enums.AppType;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Table(name = "uri")
@org.hibernate.annotations.Table(appliesTo = "uri", comment = "URI 테이블")
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class URI {
  @Id
  @Comment("URI 아이디")
  private String id;

  @Column
  @Enumerated(EnumType.STRING)
  @Comment("앱 타입")
  private AppType appType;

  @Column(name = "parent_id")
  @Comment("부모 아이디")
  private String parentId;

  @Column
  @Comment("정렬순서")
  private Integer orderNo;

  @Column
  @Comment("메뉴명")
  private String text;

  @Column
  @Comment("URI 경로")
  private String uri;

  @Column
  @Comment("노출여부")
  private Boolean showOnNavi;

  @Column
  @Comment("og 제목")
  private String ogTitle;

  @Column
  @Comment("og 설명")
  private String ogDesc;

  @Column
  @Comment("og 키워드")
  private String ogKeywords;

  @Column
  @Comment("og 이미지 URL")
  private String ogImage;

  /**
   * JPA 에서 재귀 쿼리를 구현하는 하나의 방법 - https://stackoverflow.com/questions/3638082/recursive-jpa-query
   * "This will cause multiple queries , but for small menu like things it will be ok."
   */
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @BatchSize(size = 100)
  @JoinColumn(
      name = "parent_id",
      referencedColumnName = URI_.ID,
      insertable = false,
      updatable = false)
  @OrderBy(value = URI_.ORDER_NO + " ASC")
  private Set<URI> children;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "uri_roles")
  @BatchSize(size = 100)
  private Set<String> roles;
}
