## Prerequisites

* JDK 17
* Maven
* Tomcat 9+
* RDBMS (Oracle 12+, MySQL, MSSQL, DB2 등등)

## Structure

`core`, `aws`, `admin`, `front` 모듈로 구성되어 있다.

```shell
.
|-- README.md
|-- admin # 관리자 어플리케이션
|   |-- pom.xml
|   |-- src
|   `-- target
|-- aws # AWS 서비스 전략(strategy) 모듈
|   |-- pom.xml
|   |-- src
|   `-- target
|-- core # 공통 모듈
|   |-- pom.xml
|   |-- src
|   `-- target
|-- front # 프론트 어플리케이션
|   |-- pom.xml
|   |-- src
|   `-- target
`-- pom.xml
```

* AWS 서비스를 이용하지 않는다면, 여러 pom.xml 에서 `aws`모듈 관련 내용을 삭제하면 된다.

## VM Options

프레임워크 실행 시 필요한 자바 VM 옵션은 아래와 같다.

| VM 변수                                                     | 설명                         | 설정 값                    | 필수여부 |
|-----------------------------------------------------------|----------------------------|-------------------------|------|
| -Dspring.profiles.active                                  | Spring active 프로파일         | local, dev, prod        | Y    |
| -Dapp.initData.core                                    | 초기 구동 데이터 생성 여부            | true, false             | N    |
| -Dspring.jpa.hibernate.ddl-auto                           | JPA DDL 옵션                 | update, create 등등       | Y    |
| -Dfile.encoding                                           | 범용 속성                      | `UTF-8`                 | Y    |
| -Djava.net.preferIPv4Stack                                | 범용 속성                      | true                    | Y    |
| -Dspring.jpa.properties.hibernate.show_sql                | JPA SQL 로그 보이기             | true, false             | N    |
| -Dlogging.level.org.hibernate.type.descriptor.sql         | JPA 파라미터 바인딩 로그 보이기        | trace (로그보임), info(숨기기) | N    |
| -Dcom.amazonaws.sdk.disableEc2Metadata                    | aws 모듈의 ec2 metadata 전송 끄기 | true                    | N    |
| **-Dserver.port** (2023-03-25 추가)                         | 서버 포트 (없다면 기본 8080)        | 원하는 port                | N    |
| **-Dserver.forward-headers-strategy** (2023-03-25 추가)     | 서버 헤더 설정                   | `native`                | N    |
| **-Dspring.datasource.driver-class-name** (2023-03-25 추가) | JDBC Driver                | jdbc 드라이버               | Y    |
| **-Dspring.datasource.url** (2023-03-25 추가)               | JDBC URL                   | jdbc url                | Y    |
| **-Dspring.datasource.username** (2023-03-25 추가)          | DB Username                | DB 사용자                  | Y    |
| **-Dspring.datasource.password** (2023-03-25 추가)          | DB Password                | DB 패스워드                 | Y    |

* **spring.profiles.active**  
  `local`, `dev`, `prod` 등 세가지 환경에 대한 프로파일이 미리 생성되어있다.


* **app.initData.core**  
  어플리케이션 초기 구동에 필요한 Core 데이터 생성여부.


* **spring.jpa.hibernate.ddl-auto**  
  **JPA**가 ddl 을 다루는 방식을 정의한다. 엔티티 변경이 많은 개발 시점에는 `update`, `create`를,
  엔티티 혹은 데이터가 함부로 변경되서는 안되는 운영 환경에서는 `none`, `validate` 등을 지정해준다.  
  참고 - [ddl-auto 옵션](https://smpark1020.tistory.com/140?category=857916)


* **java.net.preferIPv4Stack**  
  클라이언트 IP체크시 IPv4 형식을 사용하기 위함


* **com.amazonaws.sdk.disableEc2Metadata**  
  `aws`모듈을 사용시, metadata 전송 끄기


* **server.forward-headers-strategy**  
  (내장 컨테이너를 사용하는 경우)
  프록시 서버(nginx, apache webserver)에 정의된 `X-Forward-For`, `X-Forwarded-Proto` 설정값을 이용하려면
  `native` 로 설정해주면 된다.

### 어플리케이션 별 VM Options 예시

### 1. Admin

```
-Dspring.profiles.active=local
-Dapp.initData.core=false
-Dspring.jpa.hibernate.ddl-auto=update
-Dfile.encoding=UTF-8
-Djava.net.preferIPv4Stack=true
-Dcom.amazonaws.sdk.disableEc2Metadata=true
-Dspring.jpa.properties.hibernate.show_sql=true
-Dlogging.level.org.hibernate.type.descriptor.sql=info
-Dserver.port=8080
-Dserver.forward-headers-strategy=native
-Dlogging.level.org.springframework.security=DEBUG
-Dspring.datasource.driver-class-name=org.h2.Driver
-Dspring.datasource.url=jdbc:h2:~/h2/app-h2;AUTO_SERVER=TRUE
-Dspring.datasource.username=sa
-Dspring.datasource.password=
```

### 2. Front

```
-Dspring.profiles.active=local
-Dapp.initData.core=false
-Dspring.jpa.hibernate.ddl-auto=update
-Dfile.encoding=UTF-8
-Djava.net.preferIPv4Stack=true
-Dcom.amazonaws.sdk.disableEc2Metadata=true
-Dspring.jpa.properties.hibernate.show_sql=true
-Dlogging.level.org.hibernate.type.descriptor.sql=trace
-Dserver.port=8081
-Dserver.forward-headers-strategy=native
-Dlogging.level.org.springframework.security=DEBUG
-Dspring.datasource.driver-class-name=org.h2.Driver
-Dspring.datasource.url=jdbc:h2:~/h2/app-h2;AUTO_SERVER=TRUE
-Dspring.datasource.username=sa
-Dspring.datasource.password=
```

## JDBC

스프링부트의 JDBC 설정 방식을 그대로 이용한다. JDBC 연결을 맺을 DB Vendor 와 JNDI 이용여부 등은 프로젝트 상황에 맞게 설정하면 된다.

별도 설정이 없다면 기본 탑재된 `H2` 데이터베이스를 이용해 구동된다. `H2` 를 이용하면 로컬 개발환경에서 아래와 같은 장점을 얻는다.

* 설치 과정이 필요없다. (Maven dependency 에 기술되어 각자 환경에 이미 탑재됨)
* 가볍고 빠르며 JDBC API 를 지원하는 오픈소스이다.
* 2개의 In Memory 방식을 제공한다 (embedded mode, server mode)

`embedded`는 어플리케이션 종료와 함께 휘발되기에 영속성 유지를 위해서는 `server` 모드를 이용해야 한다.

```properties
# /home 디렉토리/h2 경로에 app-h2.mv.db 파일이 생성된다
# AUTO_SERVER 속성을 이용해 server mode 로 동작한다.
# 기본 구동시 username 은 sa, 패스워드는 없다.
-Dspring.datasource.url=jdbc:h2:~/h2/app-h2;AUTO_SERVER=TRUE
-Dspring.datasource.username=sa
-Dspring.datasource.password=
```

필독 - [H2 Server Mode 연결하기](https://javanitto.tistory.com/37)

`server` 모드로 구동되면 각자 편한 DB 클라이언트 툴을 이용해 접속할 수 있다.

## 코드 작성 규칙 (coding conventions)

기본 패키지 구조는 [포트 앤 어댑터 패턴](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))에 기반해 구성됩니다. 

![img](./docs/img/port-adapter.jpg)


getArticles 라는 주제(subject) 패키지 하부를 구성하는 ports 와 adapters 패키지를 주의깊게 봐주세요. 
```
getArticles
|-- adapters
|   |-- persistence
|   |   |-- GetArticlesDao.java # 포트아웃 구현체
|   `-- web
|       |-- GetArticlesController.java
|       `-- model
|           `-- GetArticlesReqVO.java
`-- ports
    |-- GetArticlesPortIn.java  # 포트인 인터페이스
    |-- GetArticlesPortOut.java # 포트아웃 인터페이스
    `-- application
        |-- GetArticlesService.java # 포트인 구현체
        `-- domain
            |-- GetArticles.java
            `-- GetArticlesCmd.java 
```

* ports 는 비지니스 로직을 담는 application 레이어가 위치한 곳입니다. application 은 `포트인` 인터페이스를 통해 호출되며 `포트아웃` 인터페이스를 호출해 결과를 얻습니다. 
* application 은 누가 `포트인`을 호출하는지 신경쓰지 않습니다. **자신이 호출하는 `포트아웃`의 구현체에 대해서도 모릅니다.** 이를 통해 application 은 다른 계층(web, persistence)과 관계없는 독립적인 레이어가 됩니다.
* adapters/web 은 사용자 요청을 처리하는 controller 레이어가 위치한 곳입니다. `포트인` 인터페이스를 호출하는 클라이언트로 해당 규격을 맞춘 어뎁터(adapter) 이며, **application 에 종속적입니다.** 
* adapters/persistence 는 영속화(persistence) 레이어가 위치한 곳입니다. mybatis, jpa 등 다양한 ORM 기술을 이용하여 `포트아웃` 인터페이스를 구현합니다. `포트아웃` 규격을 맞춘 어뎁터이며, **application 에 종속적입니다.** 

### 자동화 스캐폴딩 (scaffolding) 도구 소개
위 패키지 구조와 인터페이스 및 구현체를 일일이 타이핑하며 생성할 필요는 없습니다. [spring-boodup](https://github.com/beizix/spring-boodup) 스캐폴팅 도구가 자동생성 해줍니다.

### Best Practices

코드 작성 규칙에 맞게 구현된 가장 좋은 예는 관리자의 **예제 게시판**입니다. 새로운 버전에 맞춰 모든 코드들이 재작성되었고 아래 고민들에 대한 해법이 녹아있습니다.
1. 패키지 구조 및 클래스 명칭을 어떻게 잡아야 할까? 
2. form submit 과 ajax 호출. 어떤 방식이 더 이상적일까?
3. 브라우저 history 를 잘 유지하면서 back 버튼 이슈를 발생시키지 않는 구현방식은 무엇일까? 
4. 어떻게 restfull 한 서비스를 구현할 수 있을까? (GET/POST/PUT/PATCH/DELETE)
5. 새로운 파일 업로드 방식

### model mapper 에서 생성자 패턴으로의 전환
> "자바를 보다 더 컴파일 언어스럽게" 

컴파일 언어의 가장 큰 장점은 compile 시점에 발생할 수 있는 모든 오류를 작성자에게 알려주어 runtime 시 사용자가 마주하게 되는 버그를 최소화 시켜 준다는 점입니다. 
엔티티 속성이 변경되거나 추가될 때 이를 사용하는 모든 곳에서 컴파일 에러가 발생한다면 코드 수정자의 입장에서 가장 이상적인 환경이됩니다. 

model mapper 는 이러한 변경의 파급효과를 runtime 시점으로 미뤄버리기에 수정 및 리펙토링 환경에서 매우 해로운 기술입니다. JPA 관점에서도 연관 엔티티간의 조회 및 참조에서 원치않은 동작을 유발시키며 이를 제어하기는 무척 까다롭습니다. 

새로운 프레임워크 버전 부터 model mapper 가 제거되었고, 생성자 패턴을 이용해 엔티티 및 인자, 결과 객체를 교환합니다. 생성자 패턴 적용예는 **예제 게시판**에서 확인 가능합니다.

### JPA Metamodel 도입 

JPA 를 이용할 때 엔터티 클래스의 속성을 문자열로 참조해야 하는 경우가 발생합니다 (예: mappedBy="속성명").
프로젝트 진행 과정에서 해당 속성이 변경(혹은 삭제)되는 경우, 컴파일러는 이를 인지하지 못합니다. 즉, type safe 하지 않습니다.

이름이 사용되는 곳을 찾아 수작업으로 변경해주어야 하며, 이를 놓친 경우 runtime 과정에서 예상치 못한 에러를 만나게 됩니다.

JPA 메타모델은 이러한 단점을 피하고 관리되는 엔터티 클래스의 메타데이터에 대한 정적 액세스를 제공하기 위해 도입되었습니다.

`hibernate-jpamodelgen` maven 모듈을 통해 `target/generated-sources/annotations` 로 정적 JPA Metamodel 클래스들이 생성됩니다.

```
$ tree core/target/generated-sources/annotations/
core/target/generated-sources/annotations/
`-- app
    `-- module
        `-- core
            `-- config
                `-- adapter
                    `-- persistence
                        `-- entity
                            |-- ExBoardAttachment_.java
                            |-- ExBoard_.java
                            |-- FrontUser_.java
                            |-- LoggedInUserEmbeddable_.java
                            ...
```
JPA 사양에 따라 생성된 클래스는 해당 엔티티 클래스와 동일한 패키지에 상주하며 끝에 "_"(언더스코어)가 추가된 동일한 이름을 갖습니다.

이제 Metamodel 클래스를 이용해 type safe 프로그래밍을 해봅시다.

```java
// Metamodel 적용 전
@OneToMany(
        mappedBy = "exBoard",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
private Set<ExBoardAttachment> attachments;

// Metamodel 적용 후
@OneToMany(
        mappedBy = ExBoardAttachment_.EX_BOARD,
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
private Set<ExBoardAttachment> attachments;
```

정적 Metamodel 클래스는 maven compile 단계에서 생성되며 IDE 에서 어플리케이션 구동 시 빌드과정에서 자동으로 수행됩니다.

생성된 모델을 참조하기 위해서는 `target/generated-sources` 경로가 IDE 의 **classpath** 에 추가되어야 합니다.

인텔리제이의 경우, File > Project Structure > Modules > target\generated-sources 우클릭 후 Sources 체크 해주면 됩니다.

## PACKAGING

배포방식 설정을 위해 두가지 maven 프로파일을 제공한다.
* `jar` (default)
* `war` Jeus, WebLogic, Tomcat 등 별도 외장 컨테이너 이용시 사용

maven 빌드 시 프로파일을 지정하지 않으면 기본 `jar` 프로파일로 동작하고, `war` 의 경우 명시적인 지정이 필요하다.

아래는 admin 빌드시 `war` 프로파일을 지정한 예시이다.
```shell
$ mvn --projects admin --also-make clean package -DskipTests -P war

# 빌드 후, admin/target 디렉토리에 war 가 생성된걸 확인할 수 있다.
$ ll admin/target
-rw-r--r-- 1 user 197121 94905770  4월 22 09:59 admin-0.0.1-SNAPSHOT.war
```


## Documents

### 사용자 계정 및 권한 (Spring Security & Roles)

프레임워크가 선택한 Spring Security 정책과 추가 기능들을 아래 문서에서 확인할 수 있다.

[ADR-001-AUTHENTICATION_POLICY.md](./docs/arch/ADR-001-AUTHENTICATION_POLICY.md)  

### 예외처리 정책

고의적이고 명시적인 예외 발생은 사용자에게 명확한 정보를 전달합니다. 정보전달을 목적으로 예외를 발생시키는 것을 적극 권장합니다. 효율적인 예외처리 방법이 아래 문서에 담겨 있습니다.  
[예외처리 가이드](./docs/DOC-004-HANDLE_EXCEPTION.md)

### Validation 정책

일반 요청과 Ajax 요청에 관한 Validation 을 통합하여 동일한 방식으로 처리될 수 있게 설계되었습니다.
Front 화면이 아닌 서버 사이드 한곳에서만 사용자 입력값을 검증하는 정책을 권장합니다. 서버 사이드에서 손쉽게
사용자 입력값을 검증하는 방법이 아래 기술되어 있습니다.  
[Validation 가이드](./docs/validation.md)

### 파일 업로드

파일 업로드 정책과 전략, 실 사용 방법을 담은 가이드 문서입니다.

[ADR-002-FILE_UPLOAD_POLICY.md](./docs/arch/ADR-002-FILE_UPLOAD_POLICY.md)  
[ADR-003-AWS_S3_UPLOAD_STRATEGY.md](./docs/arch/ADR-003-AWS_S3_UPLOAD_STRATEGY.md)  
[DOC-002-FILE_UPLOAD.md](./docs/DOC-002-FILE_UPLOAD.md)  

### 로깅 (logback) 정책

로그 파일은 한정된 기간과 사이즈 만큼 지정된 장소에 생성되고 남겨져야 합니다.
어플 구동중에 발생한 error 로그만 별도로 구분되어 생성되기에 유지보수 관점에서 큰 이점을 가져다 줍니다.  
[로깅 가이드](./docs/Logging.md)

### URI 관리 (구:MENU 관리)

URI 정보를 통해 메뉴를 구성하고 SEO 관련 정보를 저장하는 방법이 기술되어 있습니다. 또한 사용자 권한(Role)도 매핑되어
접근제어가 실시간 반영됩니다.  
[URI 관리 및 SEO 관련 설정 가이드](./docs/Menu.md)

### 배포 가이드

## 추천 문서

### JPA 를 사용하며 준수해야할 10가지 모범예
[10 Spring Data JPA Best Practices](https://climbtheladder.com/10-spring-data-jpa-best-practices/)

### entity graph 개념과 사용법
[JPA Entity Graph](https://www.baeldung.com/jpa-entity-graph)  
[Spring Data JPA and Named Entity Graphs in Action](https://devapo.io/blog/technology/spring-data-jpa-and-named-entity-graphs-in-action/)

[JPA, Hibernate, 그리고 Spring Data JPA의 차이점](https://suhwan.dev/2019/02/24/jpa-vs-hibernate-vs-spring-data-jpa/)  
[Spring Data JPA 란? (1)](https://data-make.tistory.com/621)  
[Spring Data JPA 란? (2)](https://data-make.tistory.com/622)  