# **ADR-002:** 파일 업로드 및 참조 정책

## Context

사용자가 업로드한 파일은 전체 공개(public)를 목적으로 한다. 업로드한 파일을 참조하는데 두가지 방식이 있다. 파일 참조 시
브라우저 기본 기능이나 연결 프로그램을 통해 파일이 오픈되는 `INLINE` 방식과 
외부 간섭없이 다이렉트 파일 다운로드를 수행하는 `ATTACHMENT` 방식이다.

## Decision

`path.upload.public` 설정을 통해 업로드 경로를 지정한다. 
```properties
path.upload.public=${user.home}/upload/public
```

INLINE 파일 참조 URL 은 아래 형식으로 구성된다. 
```
/content-disposition/inline/파일경로/파일명.확장자
```

ATTACHMENT 파일 참조 URL 은 아래 형식으로 구성된다.
```
/content-disposition/attachment/파일아이디
```

## Status
`Accepted`

## Consequence

### WAS 의 `Public` `INLINE` 참조 방식 설정
`Public` 공개 범위의 `INLINE` 업로드 경로를 리소스 핸들러로 지정했다. 

```java
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/content-disposition/inline/**")
        .addResourceLocations("file:///" + publicPath + "/")
        .setCachePeriod(20);
  }
```
* 위 경로에 업로드된 파일은 스프링 @Controller 를 거치지 않고, URL 로 직접 참조가 가능하다.
* 웹서버가 없는 로컬 개발환경에서 테스트하기 적합하다.

### 웹서버의 `Public` `INLINE` 참조 방식 설정
웹서버의 경우, 가상 호스트 설정에서 Alias 를 이용해 `/content-disposition/inline` 경로와 `Public` 업로드 경로를 
연결해주면 된다. `<Directory/>` 선언을 추가해서 웹서버가 해당 디렉토리로 접근할 수 있는 권한을 부여한다.
  
```
JkUnMount /content-disposition/inline/* [워커 이름]
Alias /content-disposition/inline "[Public 업로드 경로]"

<Directory "[Public 업로드 경로]">
    Options FollowSymLinks
    AllowOverride All
    Require all granted
    Allow from all
</Directory>
```
* `JkUnMount` 를 지정해 해당 경로의 자원을 WAS 가 아닌 웹서버가 전달하도록 설정한다.

### WAS 의 `ATTACHMENT` 참조 방식 설정

`ATTACHMENT` 참조 방식은 웹서버가 아닌 WAS 에서만 처리 가능하다. 
`GetAttachmentController` 가 해당 역할을 담당한다.