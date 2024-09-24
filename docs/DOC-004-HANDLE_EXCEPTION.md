# DOC-004: 예외처리 가이드 

## Introduction

예외를 처리하는데 공통되고 일관된 처리 방식이 필요하다. 

프로그램 수행 중 발생하는 모든 예외처리는 @ControllerAdvice 객체인 `GlobalControllerAdvice.java` 가 담당합니다.

@ControllerAdvice 는 @Controller 에서 발생된 모든 예외를 처리하지만, 
Interceptor 에서 발생한 예외(스프링 시큐리티 권한체크 포함)는 처리하지 못한다는 점에 유의하세요.
이에 대한 예외 또한 커스터마이징 하고 싶다면 ErrorController 를 구현해주면 됩니다. 아래 문서를 참고하세요. 

[Spring Boot 에서 에러 페이지 처리하기](https://eblo.tistory.com/50)  
[스프링부트 커스텀 error](https://juyoungkim223.github.io/springboot/error/ErrorController/)

## Runtime 예외처리

프로그램 수행 중 발생하는 모든 예외처리는 @ControllerAdvice 객체인 `GlobalControllerAdvice.java` 가 담당합니다.   

코드 실행 시 발생하는 예측불가한 에러뿐 아니라 코드 작성자가 발생시키는 Runtime 예외 또한 해당 Advice 객체가 처리합니다.  

프레임워크는 사용자에게 정확한 정보전달을 위해 명시적으로 예외를 발생시키는 방식을 **적극적으로 권장**합니다.

아래 예제는 잘못된 adminId 전달 시 이를 사용자에게 알리는 예외처리 코드입니다.
```java
public Admin getItem(String adminId) {
  return adminRepository
    .findById(adminId)
    .orElseThrow(
      () -> new NoSuchElementException(String.format("%s 는 존재하지 않는 아이디입니다.", adminId)));
  }
```
발생된 예외는 `GlobalControllerAdvice.java` 에 의해 일반 요청인지 Ajax 요청인지를 판단 후 적절한 형식으로 반환합니다.

* 일반 요청: 에러 정보를 model 객체에 담은 뒤 에러 페이지 전달
* AJAX 요청: 에러 정보를 JSON 응답 형식으로 전달

사용자는 에러 메세지를 통해 에러 발생 원인을 파악해 볼 수 있습니다.

코드 작성자가 이용할 수 있는 예외 객체로 NoSuchElementException, IllegalArgumentException 등 자바 혹은 사용중인 모듈에서 
제공하는 기본 RuntimeException 예외객체를 이용하는걸 추천합니다. 물론 코드 작성자가 임의로 만든 커스텀 예외객체를 이용해도 됩니다.


### AJAX(비동기) 예외 처리 시 권장사항

Ajax 요청에 대한 예외처리시 `GlobalControllerAdvice.java` 는 HTTP 상태코드로 400 (HttpStatus.BAD_REQUEST) 을 전달합니다.

jQuery 를 이용한 ajax 호출 방식에서 응답 status code 에 따른 처리를 선언하는 것을 **적극적으로 권장**합니다.
```javascript
$.ajax({
    type: 'GET',
    url: '/api/uri/get/' + f.appType.value + '/' + data.id,
    statusCode: {
        // 400 - BAD_REQUEST
        400: function (response) {
            alert(response.responseJSON.message);
        }
    },
```

응답을 처리하는 Javascript 는 message 속성을 alert 로 보여주는 등의 방식으로 사용자에게 에러 내용을 보여줄 수 있습니다.

$.ajax 의 fail 콜백함수를 사용해도 되지만, 
예외 처리 이외에도 validation 실패 시 422 를 전달하는 등 다른 부가기능들도 프레임워크가 제공하기에
statusCode 선언을 통한 처리방식이 효율적입니다.

## 참고문서 
[Spring 의 다양한 예외 처리 방법](https://mangkyu.tistory.com/204)  
[DispatcherServlet 의 예외처리 전략 (Interceptor 예외처리 전략)](https://velog.io/@gillog/Java-HandlerInterceptor%EB%8B%A8-Exception-Handling%ED%95%98%EA%B8%B0HandlerExceptionResolver-ExceptionHandler)
