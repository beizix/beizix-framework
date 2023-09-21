```
██████  ███████     ██████ ██    ██  ██████ ██      ███████ 
██   ██ ██         ██       ██  ██  ██      ██      ██      
██████  █████      ██        ████   ██      ██      █████   
██   ██ ██         ██         ██    ██      ██      ██      
██   ██ ███████ ██  ██████    ██     ██████ ███████ ███████ 
```

org.beizix 은, `web`(웹), `application` (도메인), `persistence` (영속성) 세 계층을
엄격하게 분리한 스프링 부트 기반의 개발 프레임워크이다.

`web`은 사용자 요청을 검증하고 적절한 응답을 내려주는 **controller** 계층이다.

`persistence`는 데이터를 저장하고 조회하는 **repository** 계층이다. **JPA** (*Java Persistence API*) 를 이용해
특정 밴더에 종속되지 않고 모든 관계형 DB 에서 공통된 영속화 작업을 수행한다.

`application`은 비지니스 로직을 담는 **service** 계층이다.
`web`과 `persistence`는 `application`에 의존적이다.
반면 `application`은 다른 계층을 알지못하는 독립적인 주체라는 점이 org.beizix 설계의 구조적 핵심이다.

## Prerequisites

* JDK 11
* Maven
* Tomcat 9+
* RDBMS (Oracle 12+, MySQL, MSSQL, DB2 등등)

## Structure

org.beizix 은 `core`, `aws`, `admin`, `front`, `security`, `utillity` 모듈로 구성되어 있다.

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
|-- security # 스프링 시큐리티 모듈
|   |-- pom.xml
|   |-- src
|   `-- target
|-- utility # 공용 유틸리티 모듈
|   |-- pom.xml
|   |-- src
|   `-- target
`-- pom.xml
```

* AWS 서비스를 이용하지 않는다면, 여러 pom.xml 에서 `aws`모듈 관련 내용을 삭제하면 된다.
* org.beizix 은 embedded tomcat 을 이용한 jar 패키징을 사용한다.

## VM Options

org.beizix 실행 시 필요한 자바 VM 옵션은 아래와 같다.

| VM 변수                                                     | 설명                         | 설정 값                    | 필수여부 |
|-----------------------------------------------------------|----------------------------|-------------------------|------|
| -Dspring.profiles.active                                  | Spring active 프로파일         | local, dev, prod        | Y    |
| -beizix.initData.core                                   | 초기 구동 데이터 생성 여부            | true, false             | N    |
| -beizix.admin.singleRole                                | Admin 단일 권한 정책 적용여부        | true, false             | N    |
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
  org.beizix 은 `local`, `dev`, `prod` 등 세가지 환경에 대한 프로파일이 미리 생성되어있다.


* **beizix.initData.core**  
  어플리케이션 초기 구동에 필요한 Core 데이터 생성여부.


* **beizix.admin.singleRole**  
  (Admin 어플리케이션에만 해당)  Admin 단일 권한 정책 적용여부


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
-Dbeizix.initData.core=true
-Dspring.jpa.hibernate.ddl-auto=update
-Dbeizix.admin.singleRole=false
-Dfile.encoding=UTF-8
-Djava.net.preferIPv4Stack=true
-Dcom.amazonaws.sdk.disableEc2Metadata=true
-Dspring.jpa.properties.hibernate.show_sql=false
-Dlogging.level.org.hibernate.type.descriptor.sql=info
-Dserver.port=8080
-Dserver.forward-headers-strategy=native
-Dspring.datasource.driver-class-name=org.h2.Driver
-Dspring.datasource.url=jdbc:h2:~/beizix/h2/beizix-h2;AUTO_SERVER=TRUE
-Dspring.datasource.username=sa
-Dspring.datasource.password=
```

### 2. Front

```
-Dspring.profiles.active=local
-Dbeizix.initData.core=true
-Dspring.jpa.hibernate.ddl-auto=update
-Dfile.encoding=UTF-8
-Djava.net.preferIPv4Stack=true
-Dcom.amazonaws.sdk.disableEc2Metadata=true
-Dspring.jpa.properties.hibernate.show_sql=false
-Dlogging.level.org.hibernate.type.descriptor.sql=info
-Dserver.port=8081
-Dserver.forward-headers-strategy=native
-Dspring.datasource.driver-class-name=org.h2.Driver
-Dspring.datasource.url=jdbc:h2:~/beizix/h2/beizix-h2;AUTO_SERVER=TRUE
-Dspring.datasource.username=sa
-Dspring.datasource.password=
```

## PACKAGING

org.beizix 은 배포방식 설정을 위해 두가지 maven 프로파일을 제공한다.
* `jar` (default)
* `war` Jeus, WebLogic, Tomcat 등 별도 외장 컨테이너 이용시 사용

maven 빌드 시 프로파일을 지정하지 않으면 기본 `jar` 프로파일로 동작하고, `war` 의 경우 명시적인 지정이 필요하다.

아래는 admin 빌드시 `war` 프로파일을 지정한 예시이다.
```shell
$ mvn --projects admin --also-make clean package -DskipTests -P war

# 빌드 후, admin/target 디렉토리에 war 가 생성된걸 확인할 수 있다.
$ ll admin/target
-rw-r--r-- 1 beizix 197121 94905770  4월 22 09:59 admin-0.0.1-SNAPSHOT.war
```


## JDBC

org.beizix 은 스프링부트의 JDBC 설정 방식을 그대로 이용한다. JDBC 연결을 맺을 DB Vendor 와 JNDI 이용여부 등은 프로젝트 상황에 맞게 설정하면 된다.

별도 설정이 없다면 org.beizix 은 기본 탑재된 `H2` 데이터베이스를 이용해 구동된다. `H2` 를 이용하면 로컬 개발환경에서 아래와 같은 장점을 얻는다.

* 설치 과정이 필요없다. (Maven dependency 에 기술되어 각자 환경에 이미 탑재됨)
* 가볍고 빠르며 JDBC API 를 지원하는 오픈소스이다.
* 2개의 In Memory 방식을 제공한다 (embedded mode, server mode)

`embedded`는 어플리케이션 종료와 함께 휘발되기에 영속성 유지를 위해서는 `server` 모드를 이용해야 한다.

```properties
# /home 디렉토리/beizix/h2 경로에 beizix-h2.mv.db 파일이 생성된다
# AUTO_SERVER 속성을 이용해 server mode 로 동작한다.
# 기본 구동시 username 은 sa, 패스워드는 없다.
-Dspring.datasource.url=jdbc:h2:~/beizix/h2/beizix-h2;AUTO_SERVER=TRUE
-Dspring.datasource.username=sa
-Dspring.datasource.password=
```

필독 - [H2 Server Mode 연결하기](https://javanitto.tistory.com/37)

`server` 모드로 구동되면 각자 편한 DB 클라이언트 툴을 이용해 접속할 수 있다.


## SMTP

이메일 발송을 위해 core.properties 에 SMTP 계정정보(메일서버, 포트, 계정 등)를 입력한다. 물론 VM 인자로 전달해도 된다.

```properties
spring.mail.host=
spring.mail.port=465
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.ssl.enable=true
```


## Documents

### org.beizix CRUD 가이드

[DOC-000-RECYCLE-CRUD-RULES.md](./docs/DOC-000-RECYCLE-CRUD-RULES.md)

### 사용자 계정 및 권한 (Spring Security & Roles)

org.beizix 이 선택한 Spring Security 정책과 추가 기능들을 아래 문서에서 확인할 수 있다.

[ADR-001-AUTHENTICATION_POLICY.md](./docs/arch/ADR-001-AUTHENTICATION_POLICY.md)  
[DOC-001-SECURITY_ROLES.md](./docs/DOC-001-SECURITY_ROLES.md)

### 예외처리 정책

고의적이고 명시적인 예외 발생은 사용자에게 명확한 정보를 전달합니다. org.beizix 은 정보전달을 목적으로 예외를 발생시키는 것을 적극 권장합니다. 효율적인 예외처리 방법이 아래 문서에 담겨 있습니다.  
[예외처리 가이드](./docs/handleException.md)

### Validation 정책

org.beizix 은 일반 요청과 Ajax 요청에 관한 Validation 을 통합하여 동일한 방식으로 처리될 수 있게 설계되었습니다.
Front 화면이 아닌 서버 사이드 한곳에서만 사용자 입력값을 검증하는 정책을 권장합니다. 서버 사이드에서 손쉽게
사용자 입력값을 검증하는 방법이 아래 기술되어 있습니다.  
[Validation 가이드](./docs/validation.md)

### 파일 업로드

org.beizix 의 파일 업로드 정책과 전략, 실 사용 방법을 담은 가이드 문서입니다.

[ADR-002-FILE_UPLOAD_POLICY.md](./docs/arch/ADR-002-FILE_UPLOAD_POLICY.md)  
[ADR-003-AWS_S3_UPLOAD_STRATEGY.md](./docs/arch/ADR-003-AWS_S3_UPLOAD_STRATEGY.md)  
[DOC-002-FILE_UPLOAD.md](./docs/DOC-002-FILE_UPLOAD.md)  
[DOC-003-FILE_RETRIEVE.md](./docs/DOC-003-FILE_RETRIEVE.md)

### 로깅 (logback) 정책

로그 파일은 한정된 기간과 사이즈 만큼 지정된 장소에 생성되고 남겨져야 합니다.
어플 구동중에 발생한 error 로그만 별도로 구분되어 생성되기에 유지보수 관점에서 큰 이점을 가져다 줍니다.  
[로깅 가이드](./docs/Logging.md)

### URI 관리 (구:MENU 관리)

URI 정보를 통해 메뉴를 구성하고 SEO 관련 정보를 저장하는 방법이 기술되어 있습니다. 또한 사용자 권한(Role)도 매핑되어
접근제어가 실시간 반영됩니다.  
[URI 관리 및 SEO 관련 설정 가이드](./docs/Menu.md)

### 테스트 자동화

org.beizix 은 @SpringBootTest 어노테이션을 이용한 통합테스트 자동화에 중점을 둡니다.  
[통합테스트 작성 가이드](./docs/TEST_GUIDE.md)

### AJAX Best Practice

[Ajax 가이드](./docs/AJAX_BEST_PRACTICE.md)

### UI 코드 (구:공통코드)

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
