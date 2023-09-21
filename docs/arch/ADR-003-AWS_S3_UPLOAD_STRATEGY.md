# **ADR-003:** AWS S3 파일 업로드 전략

## Context

로컬 스토리지를 이용해 파일을 업로드하는 기본 전략 (참고: [ADR-002-FILE_UPLOAD_POLICY.md](./ADR-002-FILE_UPLOAD_POLICY.md)) 이외에 AWS S3 로 
파일을 올리고 CloudFront 를 통해 파일이 제공되는 새로운 전략이 필요했다.

## Decision

org.beizix 은 AWS S3 파일 업로드 기능 제공을 위해 새로운 전략을 구현한다. 
새로운 전략은 기존과 동일한 파일 업로드 서비스 인터페이스를 이용하며, 
`Public`, `Private` 공개범위를 가진다.

공개범위에 따른 `INLINE`, `ATTACHMENT` 응답 해더는 구현 가능한 범위 내에서 한정적으로 제공한다.

## Status
`Accepted`

## Consequence

`aws` maven 서브 모듈이 추가되었다. S3 파일 업로드 전략과 CloudFront 도메인으로 파일을 참조하는 전략을 담고 있다. 
(훗날 새로운 AWS 기능이 필요하면 이 모듈에 추가될 것이다.)

S3 업로드가 필요하다면 `aws` 모듈을 종속성에 추가해주면 된다.
```xml
<dependency>
    <groupId>org.beizix</groupId>
    <artifactId>aws</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

`aws` 모듈의 `aws.properties` 와 `aws-{spring.active.profile}.properties` 에 
S3와 CloudFront 를 이용하기 위한 설정 정보를 기입해야 한다.

`Content-Disposition` 은 `INLINE` 참조 방식만 제공한다.

| 공개 범위 | Content-Disposition | URL | 
|---|---|---|
| `Public` | `INLINE` | ${beizix.aws.cloudfront.domain}/파일경로/파일명.확장자
|  | `ATTACHMENT` | X
| `Private` | `INLINE` | X
|  | `ATTACHMENT` | X 

* 추후 실제 수요가 있을 때 나머지 방식들을 구현할 예정이다.
