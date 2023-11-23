package org.beizix.core.adapter.persistence.uicode.model;

import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "uicode")
@org.hibernate.annotations.Table(appliesTo = "uicode", comment = "UI 코드 테이블")
@AllArgsConstructor
@Builder
public class UICode {
  @Id
  @Comment("코드 아이디")
  private String id;

  @Column
  @Comment("부모 아이디")
  private String parentId;

  @Column
  @Comment("정렬순서")
  private Integer orderNo;
  
  @Column
  @Comment("코드명")
  private String codeText;
  
  @Column
  @Comment("코드값")
  private String codeValue;
  
  @Column
  @Comment("메세지 코드")
  private String msgCode;
  
  @Column
  @Comment("사용여부")
  private Boolean inUse;

  /**
   * JPA 에서 재귀 쿼리를 구현하는 하나의 방법 - https://stackoverflow.com/questions/3638082/recursive-jpa-query
   * "This will cause multiple queries , but for small menu like things it will be ok."
   */
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @JoinColumn(
      name = "parentId",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private Set<UICode> children;
}
