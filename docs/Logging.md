# Logging (logback) 가이드

로깅 설정은 구동중인 인스턴스의 `logback-spring.xml`과 `application-${spring.profiles.active}.properties` 에 기술되어 있습니다.  

logback-spring.xml
```xml
<configuration>
    ...
    <property name="APP_LOG_FILE" value="admin-app"/>
    ...
</configuration>
```

application-local.properties
```properties
#
#  █████╗ ██████╗ ██████╗     ██╗      ██████╗  ██████╗  ██████╗ ██╗███╗   ██╗ ██████╗
# ██╔══██╗██╔══██╗██╔══██╗    ██║     ██╔═══██╗██╔════╝ ██╔════╝ ██║████╗  ██║██╔════╝
# ███████║██████╔╝██████╔╝    ██║     ██║   ██║██║  ███╗██║  ███╗██║██╔██╗ ██║██║  ███╗
# ██╔══██║██╔═══╝ ██╔═══╝     ██║     ██║   ██║██║   ██║██║   ██║██║██║╚██╗██║██║   ██║
# ██║  ██║██║     ██║         ███████╗╚██████╔╝╚██████╔╝╚██████╔╝██║██║ ╚████║╚██████╔╝
# ╚═╝  ╚═╝╚═╝     ╚═╝         ╚══════╝ ╚═════╝  ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝ ╚═════╝
logging.file.path=${user.home}/logs
#
# application 의 기본 패키지(app.module) 와 로깅 레벨(DEBUG) 설정
# 기본 패키지 변경 시 app.module 부분을 반드시 치환해 주어야 application 로그가 출력되니 유의
logging.level.app.module=DEBUG
```

* `logging.file.path` 로그파일이 생성될 위치
* `logging.level`.app.module 여러분의 어플리케이션 기본 패키지. 해당 패키지 하부의 로그 출력 Level을 설정합니다.

### 생성 로그 파일
`logging.file.path` 경로에 두가지 파일이 생성됩니다.

|로그파일|설명|
|---|---|
|`APP_LOG_FILE`.log| 전체 로그 파일|
|`APP_LOG_FILE`-ERROR.log| 에러 로그 파일|

에러 로그 파일에는 전체 로그 파일에서 ERROR 부분을 필터링해 별도로 생성한 파일입니다. 이는 운영관점에서 큰 이점을 가져다줍니다. 

로그 보관 주기나 파일 Rolling 정책 등은 /src/main/resources/`logback-spring.xml` 을 확인하세요. 
