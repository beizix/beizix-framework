# DOC-001: 인증과 권한

## Introduction

org.beizix 은 스프링 시큐리티를 이용해 사용자를 인증하고 권한을 부여한다. 
본 문서는 org.beizix 에 적용한 스프링 시큐리티 설정과 인증과 권한 체크 로직을 설명한다. 

## Scope

`Front` 모듈은 프로젝트 요구사항에 따라 인증작업이 필요없는 경우도 있다. org.beizix 은 
`Admin` 모듈에 인증작업을 구현한다. 

## Implementation

권한과 인증은 어플리케이션을 구성하는데 가장 중요한 정책입니다. 
스프링 시큐리티는 역할을 의미하는 `Role` 과 권한을 뜻하는 `Authority`와 `Previlege`를 혼용해 사용자들을 혼란스럽게 합니다. 

스프링 시큐리티에서 hasAnyRole 과 hasAnyAuthorities 는 같은 의미의 메서드 입니다.
이 맥락에서 역할(`Role`)과 권한(`Authority`)은 같은 의미라고 받아들여집니다. `Previlege`는 어떤 객체나 메서드와도 매핑되지 않는 단지 개념에 불과합니다. 
하지만 의미상으로 **권한**으로 해석되며 이는 다시 `Authority` 와 동일한 개념으로 보여집니다.

누군가는 `Role` 을 `Previlege` 의 집합으로 구성하기도 하고, 다른 누군가는 `Previlege` 라는 개념자체를 사용하지 않기도 합니다. 

org.beizix 은 후자의 경우 입니다. 가장 단순하며 명확한 정책을 적용해 역할(`Role`)과 권한(`Authority`)은 동일한 개념이며
`Previlege`는 존재하지 않습니다. 

사용자는 각자의 권한(혹은 역할)목록을 가지며, 접근하는 URI 에 부합하는 권한을 가졌을 때 접근할 수 있습니다.

## 사용자와 권한 매핑


관리자별 권한 할당은 `Settings > 관리자` 에서 부여할 수 있습니다.

신규 권한을 생성하려면 `Settings > 관리자 권한`에서 추가할 수 있습니다.

`Settings > URI 관리` 는 특정 URI 에 접근할 수 있는 관리자 권한을 매핑합니다. 권한을 매핑하지 않은 경우 모든 관리자가 접근 가능합니다.

## 사용자 인증 및 URI 별 접근 권한 정책

Spring Security 의 Lambda DSL(antMatchers 등등) 방식으로 전체 어플리케이션의 기본 접근제어를 설정했습니다. (`AdminSecurityConfig.java` 참고)

특정 URI 에 접근할 수 있는 권한 설정은 Lambda 선언이 아닌 Database 에 기반을 둡니다.  
이는 권한 설정 변경 시 재배포 없이 바로 반영되게 하기 위함입니다. `Settings > URI 관리` 화면에서 URI 와 권한 매핑이 실시간 반영됩니다.

