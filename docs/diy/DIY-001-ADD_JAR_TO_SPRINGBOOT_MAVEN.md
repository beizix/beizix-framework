# DIY-001: 스프링부트 Maven 프로젝트에 jar 라이브러리 추가하기

## Introduction

Maven 기반 스프링부트 프로젝트 구성에는 WEB-INF/lib 디렉토리가 없다. 
외부 경로를 통해 얻은 jar 를 프로젝트에 포함시키려면 아래 작업이 필요하다.

이 예제는 front 모듈에 외부 jar 파일(INIpaySample_v1.3.jar)을 인식시키는 과정을 예로 든다.

## webapp/WEB-INF/lib 디렉토리 생성

src/main 밑에 `webapp/WEB-INF/lib` 디렉토리를 생성한다.

```shell
.
|-- pom.xml
|-- src
|   |-- main
|   |   |-- java
|   |   |-- resources
|   |   `-- webapp
|   |       `-- WEB-INF
|   |           `-- lib
|   |               `-- INIpaySample_v1.3.jar
```

이 경로에 담긴 jar 파일은 패키징(`war` or `jar`) 결과물의  WEB-INF/lib 경로에 자동 포함된다. 

물론 패키징 결과물을 만들기 위해서 빌드(`compile`)과정이 성공해야 한다.
이를 위해 spring-boot-maven-plugin 이 필요하다

## spring-boot-maven-plugin 플러그인


front/pom.xml 에 아래 내용을 추가한다.

```xml
    <properties>
        ...
        <webapp.lib>${basedir}/src/main/webapp/WEB-INF/lib</webapp.lib>
    </properties>
    
    <dependencies>
        ...
        <dependency>
            <groupId>external</groupId>
            <artifactId>external</artifactId>
            <version>1.0</version>
            <scope>system</scope>
            <systemPath>${webapp.lib}/INIpaySample_v1.3.jar</systemPath>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.6.3</version>
                <configuration>
                    <includeSystemScope>true</includeSystemScope>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

**중요**  
위 예제에서 인클루드된 jar 파일은 `system` 스코프를 가진다. `system`은 `provided`와 동일하게 
runtime 실행 시 JDK 혹은 외부 Container 에서 제공된다는 의미기에 최종 빌드과정에서는 포함되지 않는다. 
하지만 src/main/webapp 이라는 maven 규약 경로에 WEB-INF/lib 을 만들 었기에 최종 빌드 결과물의 WEB-INF/lib 에 담기게 된다.
