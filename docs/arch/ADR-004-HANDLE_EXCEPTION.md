# **ADR-004:** 예외 처리 

## Context

웹 어플리케이션 실행 중 발생하는 예외를 다루는데 공통되고 일관된 처리 방식이 필요하다.
동기와 비동기 요청을 수행하면서 예기치 않은 오류가 발생했을 때 일관된 포맷을 유지하며 사용자에게 전달되어야 한다. 

## Decision

**@ControllerAdvice** 를 이용해 모든 `@Controller`와 `@RestController`에서 발생하는 예외를 처리한다.  
동기식 요청일 경우 500 에러 페이지로, 비동기 요청일 경우 규격화된 JSON 포맷으로 예외 메세지를 전달한다. 

## Status
`Accepted`

## Consequence

작성중
