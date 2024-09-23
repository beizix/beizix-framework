```
                            /          /o       
 _   _   __  o ____  _,    /____ __ __/ '. . _  
/_)_/_)_/ (_<_/ / <_(_)_  /_)(_)(_)(_/_ (_/_/_)_
   /                 /|                    /    
  '                 |/                    '     

- scaffolding tool for spring developer. 
```

`spring bood'up`은 [포트 앤 어댑터 패턴](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))에 기반한 패키지 구조를 생성하고 
인터페이스 및 구현체를 생성하는 scaffolding 도구 입니다.

GetArticles (기사 목록 불러오기) 라는 주제 명칭으로 명령어를 실행하면 아래 구조를 만들어줍니다.  

```
$ tree getArticles
getArticles
|-- adapters
|   |-- persistence
|   |   |-- GetArticlesDao.java
|   `-- web
|       |-- GetArticlesController.java
|       `-- model
|           `-- GetArticlesReqVO.java
`-- ports
    |-- GetArticlesPortIn.java
    |-- GetArticlesPortOut.java
    `-- application
        |-- GetArticlesService.java
        `-- domain
            |-- GetArticles.java
            `-- GetArticlesCmd.java

7 directories, 8 files
```

## Application

application 은 비지니스 로직을 담는 `service` 계층입니다. 
service 를 호출하는 포트인, service 가 호출할 포트아웃 인터페이스와 구현체를 만듭니다.

| 순서  | Argument                                    | 설명                                                                                                                                             |
|:---:|---------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
|  1  | {생성 경로}                                     | ports & apdaters 패키지 및 인터페이스가 생성될 경로                                                                                                           |
|  2  | {주제 명칭}                                     | 인터페이스 및 도메인 객체 네이밍 지정에 사용된다                                                                                                                    |
|  .  | --list, --void, --pageable, <br/>--optional | 메서드 반환 타입 지정 (기본값: --optional)                                                                                                                 |
| . | --impl                                      | (Optional) 인터페이스 구현체를 생성한다.                                                                                                            
| . | --clear | (Optional) {생성 경로}를 생성하기 전에 삭제를 먼저 수행한다 


Pageable 반환 scaffolding 실행
```shell
$ ./application.sh ../admin/src/main/java/app/module/admin/usecase/article/getArticles GetArticles --pageable --impl
```

List 반환 scaffolding 실행
```shell
$ ./application.sh ../admin/src/main/java/app/module/admin/usecase/article/getArticles GetArticles --list --impl
```

void scaffolding 실행
```shell
$ ./application.sh ../admin/src/main/java/app/module/admin/usecase/article/getArticles GetArticles --void --impl
```

기본(Optional) 반환 scaffolding 실행
```shell
$ ./application.sh ../admin/src/main/java/app/module/admin/usecase/article/getArticles GetArticles --impl
```

## Web

web 은 사용자 요청 검증 및 응답을 제공하는 `controller` 계층입니다. 요구사항에 맞는 응답을 제공하기 위해 포트인 인터페이스를 호출합니다.

| 순서  | Argument | 설명                                                                                                                                                              |
|:---:|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  1  | {생성 경로}  | Controller 와 VO 객체를 생성할 경로                                                                                                                                      |
|  2  | {주제 명칭}  | Controller 와 VO 네이밍 지정에 사용된다                                                                                                                                    |
|  3  | {URI}    | Controller 에 매핑될 URI 주소                                                                                                                                         |
|  .  | --get, --post, --put, <br/>--patch, --delete  | HTTP 메서드 지정                                                                                                                                                     |
| . | --rest | (Optional) Rest Controller 생성시 지정한다                                                                                                                             
| . | --pageable | (Optional) GET 방식일 경우, Controller 메서드에 pageable 인자를 추가한다.                                                                                                       |
| . | --accept-form, <br/>--accept-json | (Optional) POST/PUT/PATCH/DELETE 방식일 경우, 수용 content-type 을 지정한다.<br/>--accept-form: application/x-www-form-urlencoded (기본값)<br/>--accept-json: application/json |                                  
| . | --clear | (Optional) {생성 경로}를 생성하기 전에 삭제를 먼저 수행한다                                                                                                                         

Get Controller (rest)
```shell
$ ./web.sh ../admin/src/main/java/app/module/admin/usecase/article/getArticles/adapters/web GetArticles /api/board/articles --get --rest
```

Get Controller (with Pageable)
```shell
$ ./web.sh ../admin/src/main/java/app/module/admin/usecase/article/getArticles/adapters/web GetArticles /api/board/articles --get --rest --pageable
```

Post Rest Controller
```shell
$ ./web.sh ../admin/src/main/java/app/module/admin/usecase/article/createArticle/adapters/web CreateArticle /ap
i/board/articles/create --post --rest
```