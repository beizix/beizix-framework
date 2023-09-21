# org.beizix CRUD 가이드

# 공통 규칙

Entity 사이의 관계는 양방향 매핑으로 촘촘하게 연결한다. ddl-auto `create` 옵션일 때, 
양방향 매핑 관계를 통해 **참조 제약**에 어긋나지 않게 순서대로 **테이블이 삭제되고 다시 생성**되는 것이 가능해진다.

Entity 조회시 `projection`을 이용한다. [참고 - Spring Data JPA Projections](https://www.baeldung.com/spring-data-jpa-projections). 
Entity 를 조회 반환 객체로 사용하는건 관계가 복잡해질 수록 감당해야하는 비용이 커지기에 주의가 필요하다.


## 조회 (목록/상세) 기본 규칙 

### 목록 조회

1. 목록은 검색과 정렬, 페이징 처리를 용이하게 하기 위해 `Specification`을 이용한다.  
2. `Specification` 이용 시 `projection`을 선언할 수 없었지만,
[specification-with-projection](https://github.com/pramoth/specification-with-projection) 
라이브러리 도움으로 둘을 함께 사용할 수 있게 되었다! 
3. 조회의 반환 값으로 엔티티가 아닌 `projection` 선언해 사용하자.
      공통 규칙에서 언급했듯이 Entity 자체로 조회하는건 관계가 복잡해질 수록 비용이 증가한다.

```java
// AdminUserListInfo 는 `projection`. spec과 pageable 과 함께 동작한다.

adminUserRepo.findAll(spec, AdminUserListInfo.class, pageable);
```

4. `Pageable`과 `@EntityGraph` 는 함께 사용할 수 없다. 목록 구성(페이징 처리 포함)을 위한 
Specification 의 페이징 처리 방식은 @BatchSize 선언이 가장 효율적이며, 
EntityGraph 의 객체 그래프 선언은 JPA 가 페이징 처리를 하는데 메모리를 과도하게 사용할 위험이 있기 때문이다.
5. Entity 간의 참조 관계는 가급적 **FetchType.LAZY** 로 선언하고, 상황에 맞게 `projection` 을 선언해 필요한 정보를 가져오자.


### 상세 조회

1. 상세 조회 시 `@EntityGraph`를 적극적으로 선언해 사용한다.
[참고 - Spring Data JPA and Named Entity Graphs in Action](https://devapo.io/blog/technology/spring-data-jpa-and-named-entity-graphs-in-action/).

```java
// Entity Graph 선언으로, AdminUser 와 연관된 엔티티들의 정보를 JOIN 쿼리문으로 효율적으로 가져오게 된다.

@NamedEntityGraph(
    name = "view_details_entity_graph",
    attributeNodes = {@NamedAttributeNode(value = "withRoles", subgraph = "role_subgraph")},
    subgraphs = {
      @NamedSubgraph(
          name = "role_subgraph",
          attributeNodes = {@NamedAttributeNode("roleDto")})
    })
public class AdminUserEntity {
```

2. 조회의 반환 값으로 엔티티가 아닌 `projection` 선언해 사용하자. 
공통 규칙에서 언급했듯이 Entity 자체로 조회하는건 관계가 복잡해질 수록 감당해야하는 비용이 커진다.

```java
// AdminUserViewInfo 는 `projection`. `view_details_entity_graph`는 쿼리 수행시 이용할 entity graph 이름이다.

@EntityGraph("view_details_entity_graph")
Optional<AdminUserViewInfo> findAdminUserById(String id);
```

3. Entity 관계는 가급적 **FetchType.Lazy** 로 선언한다.
   상황에 맞게 `projection` 을 선언해 필요한 관계를 가져올 수 있고, 상세 조회의 경우 `@EntityGraph`를 활용해 runtime 시 Entity 관계를 바꿔줄수도 있다.


## 저장 (생성/수정) 기본 규칙

생성과 수정 작업, 둘다 JpaRepository 의 save API 를 사용할 수 있다. JPA 관점에서 두 작업의 차이는 
고유 식별값인 @Id 값의 유무이다. 생성과 달리 수정 작업의 경우, `JPQL`을 활용해 보다 효율적으로 작업을 진행할 수 있다. 

### 수정 Update

1. 수정 시, 가능하다면 `JPQL`을 이용해 변경이 필요한 속성값만을 repository 로 전달한다.

```java
// JPQL 을 이용해 최소한의 조회 조건으로 필요한 속성만 변경한다. 

@Modifying
@Query("update AdminUserEntity e set e.accountLocked = :accountLocked where e.id = :id")
void updateAccountLocked(String id, boolean accountLocked);
```

2. Entity 속성 대부분이 변경되는 경우, JpaRepository API 를 이용한다. 영속성 컨텍스트 안에서 다건의 트랜젝션 발생 과정에서 
수정된 Entity 정보를 참조하는 경우 의도치 않은 동작을 경험할 수 있으니 주의하자.
3. **CascadeType.MERGE** 를 활용하자. 부모 Entity 가 여러 자식 Entity 를 가질 때(@OneToMany), 부모가 수정(update)되면
담고 있던 자식 객체들을 자동 영속화 해준다. (주의. 생성만 해주기에 기존 자식 정보들은 명시적으로 삭제해주어야 한다.)

### 생성 Create
1. 생성 시 JpaRepository API 를 이용한다. 영속성 컨텍스트 안에서 다건의 트랜젝션 발생 과정에서 생성된 Entity 정보를 참조해야
한다면 명시적으로 repository.flush() 를 호출해줘야 할 수도 있다.
2. 생성 한 Entity 를 바로 조회해서 최신의 상태로 반환해주는건 좋지만, 다건의 트랜잭션 발생 과정에서 상당한 지연(latency) 을 발생시킬 수 있으니 주의가 필요하다.

```java
/* 잘못된 패턴. 생성된 엔티티를 modelMapper 다시 매핑해주기에 연관된 엔티티 조회가 발생한다. bulk 성 작업시 지연초래. */
@Override
@Transactional
public Optional<AdminUser> operate(AdminUser adminDto) {
  AdminUserEntity createdItem = adminUserRepo.save(modelMapper.map(adminDto, AdminUserEntity.class));
  return Optional.of(modelMapper.map(createdItem, AdminUser.class));
}

/* 올바른 패턴. 전달된 모델 객체를 그대로 리턴한다.*/
@Override
public Optional<AdminUser> operate(AdminUser adminDto) {
  adminUserRepo.save(modelMapper.map(adminDto, AdminUserEntity.class));
  return Optional.of(adminDto);
}
```

2. **CascadeType.PERSIST** 를 활용하자. 부모 Entity 가 여러 자식 Entity 를 가질 때(@OneToMany), 부모가 생성(create)되면
   담고 있던 자식 객체들을 자동 영속화 해준다.

## 삭제 기본 규칙

1. **CascadeType.REMOVE** 를 활용하자. 부모 Entity 가 여러 자식 Entity 를 가질 때(@OneToMany), 부모가 삭제되면
   담고 있던 자식 객체들을 자동 삭제 해준다.
