# DOC-003: 파일 참조(다운로드) 

## Introduction

`LOCAL`과 `S3` 업로드 전략별로 파일은 다른 경로에 담긴다.
응답헤더의 `content-disposition` 설정에 따라 파일 참조(다운로드) 방식은 `INLINE`과 `ATTACHMENT` 방식으로 나뉜다.

어떤 경우든 HTTP 프로토콜로 참조해야 하는데, 참조 URL 을 생성하는데 편리한 방식을 소개한다.


## Interface

**FileUrlService** 의 operate 인터페이스를 호출해 참조 URL 을 얻는다.

|Arguments|Description
|---|---
|ContentDispositionType| `INLINE`, `ATTACHMENT`
|FileUploadInfo| 저장된 파일 정보

|반환 타입|설명
|---|---
|String| HTTP URL 주소

## Set up
N/A

## How to use
```javascript
// HTML 사용 예
<a th:href="${@fileUrlService.operate('ATTACHMENT', item.fileUploadOutput)}">
```

```java
// Java 사용 예
fileUrlService.operate(ContentDispositionType.ATTACHMENT, item.fileUploadOutput);
```

