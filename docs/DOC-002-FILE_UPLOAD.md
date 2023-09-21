# DOC-002: 파일 업로드 

## Introduction

파일을 업로드하는데 `LOCAL`과 `S3` 두가지 전략이 있다. `LOCAL`은 WAS 가 접근가능한 저장공간을 제어하는 정책(기본)이며,
`S3`는 AWS의 저장 스토리지를 다루는 전략이다. 
어떤 전략을 취하든 우리는 동일한 인터페이스를 이용하면 된다. 

## Interface

**FileUploadService** 의 operate 메서드를 호출해 파일 업로드를 수행한다.

|Arguments|Description
|---|---
|FileUploadType| 파일 타입과 업로드 전략 등 파일 업로드에 필요한 정보를 담은 enum 객체
|MultipartFile| 업로드 Multipart File
* FileUploadType 에 정의한 FileStorageType 설정값(`Local` or `S3`) 을 통해 업로드 공간이 결정된다.

|반환 타입|설명
|---|---
|Optional\<FileUploadInfo\>| ...


## Set up

파일 업로드에 관한 공통 설정은 `core.properties` 에 선언했다.

```properties
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=50MB
spring.servlet.multipart.file-size-threshold=1MB
```

스프링 profile 별로 차별화된 설정은 `core-${spring.profiles.active}.properties` 에 기술한다.

(예) core-local.properties
```properties
# `Public`, `Private` 공개범위별 업로드 경로
path.upload.public=${user.home}/recycle/upload/public
path.upload.private=${user.home}/recycle/upload/private

# file-size-threshold 용량이 넘는 파일 업로드시 사용할 임시 디렉토리
path.upload.tmpdir=${user.home}/recycle/upload/tmpdir
spring.servlet.multipart.location=${path.upload.tmpdir}
```

* `path.upload.tmpdir`는 multipart 용량이 `file-size-threshold`을 초과할 때
  임시적으로 파일을 저장 할 수 있는 공간이다. 


## How to use

파일 업로드는 `FileUploadService` 를 이용한다.
```java
// 예제 게시판 업로드
FileUploadService.operate(FileUploadType.EXAMPLE_PUBLIC, multipartFile);
```

* `FileUploadType` 은 파일 업로드 타입과 저장 경로, 그리고 외부 공개여부에 대한 정보를 정의한 enum 객체이다.

* `multipartFile` 사용자가 선택한 파일 객체. 

FileUploadType 객체는 파일 업로드와 관한 필수 정보를 담고 있다. 
**추후 새로운 파일 업로드 기능이 추가 될때 마다 이곳에 관련 정보를 기술**해야합니다. 

```java
// FileUploadType.EXAMPLE_PUBLIC 에 정의된 정보
...
EXAMPLE_PUBLIC(
  FileStorageType.LOCAL,    // fileStorageType
  true,                     // public 여부 boolean
  "/exampleBoard",          // sub 디렉토리
  Set.of(                   // 허용 타입
    AcceptableFileType.IMAGE,
    AcceptableFileType.EXCEL,
    AcceptableFileType.PPT,
    AcceptableFileType.HWP,
    AcceptableFileType.WORD,
    AcceptableFileType.CSV,
    AcceptableFileType.TXT,
    AcceptableFileType.PDF,
    AcceptableFileType.ZIP)),
```

* `fileStorageType`은 파일이 저장될 공간. `LOCAL`과 `S3`가 제공된다.
* `public` 이 true 면 공개 파일로, false 면 private 파일로 인식.  
* `subPath`는 게시판에 따른 분류 등으로 사용할 때 이용. 공지사항 게시물의 업로드 파일이기에 /exampleBoard 서브 경로에 담도록 지정했다.  
* `acceptableFileTypes` 에 허용할 파일 타입을 명시. 지정된 파일 타입과 다른 파일이 업로드 될때 `UnAcceptableFileExceoption` 이 발생한다.


### acceptableFileTypes
`FileUploadService`는 `acceptableFileTypes` 에 기술된 
FileType 을 기준으로 검증작업을 수행한다. multipartFile 을 inputstream 으로 읽어 MIME Type 적합성 여부를 판단한다.

## References
[파일 업로드 시 MIME 타입 확인](https://github.com/devheedoo/TIW/blob/master/%5BJava%5D%20%ED%8C%8C%EC%9D%BC%20%EC%97%85%EB%A1%9C%EB%93%9C%20%EC%8B%9C%20MIME%20%ED%83%80%EC%9E%85%20%ED%99%95%EC%9D%B8.md)
