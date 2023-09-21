# **ADR-002:** 파일 업로드 및 참조 정책

## Context

사용자가 파일을 업로드 하는데 두가지 목적이 있다. 전체 공개를 목적으로 한 `Public`과
사적 공유를 목적으로 한 `Private`이 있다. 

사용자가 파일을 참조할 때 두가지 방식이 있다. 파일 참조 시
브라우저 기본 기능이나 연결 프로그램을 통해 파일이 오픈되는 `INLINE` 방식과 
외부 간섭없이 다이렉트 파일 다운로드를 수행하는 `ATTACHMENT` 방식이다.

파일을 참조하는 방식은 파일을 업로드하는 방식을 결정하는데 큰 영향을 끼친다. 
`Public` 업로드 파일은 WAS 이 외에도 특정 웹서버나 CDN 이 접근할 수 있는 경로에 올려져야 한다. 
`Private` 업도르 파일은 웹 어플리케이션의 권한체크 로직이 
수반되기에 WAS 가 제어할 수 있는 경로에 올려져야 하고 이외에 다른 서비스는 접근할 이유가 없다.


## Decision

org.beizix 은 `Public`과 `Private` 업로드 방식을 제공하며 properties 설정을 통해 업로드 경로를 지정한다. 
```properties
path.upload.public=${user.home}/recycle/upload/public
path.upload.private=${user.home}/recycle/upload/private
```

사용자에게 파일을 제공할 때 응답헤더의 `content-disposition` 설정을 통해 `INLINE`과 `ATTACHMENT` 방식을 제공한다. 
아래 코드는 `Attachment` 방식으로 사용자에게 전달되는 예이다. 
```java
return ResponseEntity.ok()
  .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
  .body(resource);
```

파일 참조 URL 은 아래 형식으로 구성된다. 
```
/content-disposition/{inline or attachment}/{public or private}/파일경로/파일명.확장자
```

## Status
`Accepted`

## Consequence

현재 구현된 항목은 아래와 같다. 외부 공개 여부와 Content-Disposition 설정값에 따라 아래와 같이 분류된다. 

| 공개 범위 | Content-Disposition | URL | 
|---|---|---|
| `Public` | `INLINE` | /content-disposition/**inline**/**public**/...
|  | `ATTACHMENT` | /content-disposition/**attachment**/**public**/…
| `Private` | `INLINE` | X
|  | `ATTACHMENT` | /content-disposition/**attachment**/**private**/…

* `Private` 공개 범위의 `INLINE` 은 불필요하다고 판단되어 현재는 제공되지 않는다. 


### WAS 의 `Public` `INLINE` 참조 방식 설정
org.beizix 은 `Public` 공개 범위의 `INLINE` 업로드 경로를 리소스 핸들러로 지정했다. 

```java
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/content-disposition/inline/public/**")
        .addResourceLocations("file:///" + publicPath + "/")
        .setCachePeriod(20);
  }
```
* 위 경로에 업로드된 파일은 스프링 @Controller 를 거치지 않고, URL 로 직접 참조가 가능하다.
* 웹서버가 없는 로컬 개발환경에서 테스트하기 적합하다.

### 웹서버의 `Public` `INLINE` 참조 방식 설정
웹서버의 경우, 가상 호스트 설정에서 Alias 를 이용해 `/content-disposition/inline/public` 경로와 `Public` 업로드 경로를 
연결해주면 된다. `<Directory/>` 선언을 추가해서 웹서버가 해당 디렉토리로 접근할 수 있는 권한을 부여한다.
  
```
JkUnMount /content-disposition/inline/public/* [워커 이름]
Alias /content-disposition/inline/public "[Public 업로드 경로]"

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
`ContentDispositionAttachController` 가 해당 역할을 담당한다.

