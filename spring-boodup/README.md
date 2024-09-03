## Application

| 순서  | Argument                                    | 설명                                                                                                                                             |
|:---:|---------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
|  1  | {생성 경로}                                     | ports & apdaters 패키지 및 인터페이스가 생성될 경로                                                                                                           |
|  2  | {주제 명칭}                                     | 인터페이스 및 도메인 객체 네이밍 지정에 사용된다                                                                                                                    |
|  .  | --list, --void, --pageable, <br/>--optional | 메서드 반환 타입 지정 (기본값: --optional)                                                                                                                 |
| . | --impl                                      | (Optional) 인터페이스 구현체를 생성한다.                                                                                                            
| . | --clear | (Optional) {생성 경로}를 생성하기 전에 삭제를 먼저 수행한다 


Pageable 반환 scaffolding 실행
```shell
./application.sh ../core/src/main/java/org/beizix/core/usecase/user/removeRoleWithPrivilege RemoveRoleWithPrivilege --pageable --impl
```

List 반환 scaffolding 실행
```shell
./application.sh ../core/src/main/java/org/beizix/core/usecase/user/removeRoleWithPrivilege RemoveRoleWithPrivilege --list --impl
```

void scaffolding 실행
```shell
./application.sh ../core/src/main/java/org/beizix/core/usecase/user/removeRoleWithPrivilege RemoveRoleWithPrivilege --void --impl
```

기본(Optional) 반환 scaffolding 실행
```shell
./application.sh ../core/src/main/java/org/beizix/core/usecase/user/removeRoleWithPrivilege RemoveRoleWithPrivilege --impl
```

## Web

| 순서  | Argument | 설명                                                                                                                                             |
|:---:|----------|------------------------------------------------------------------------------------------------------------------------------------------------|
|  1  | {생성 경로}  | Controller 와 VO 객체를 생성할 경로                                                                                                                     |
|  2  | {주제 명칭}  | Controller 와 VO 네이밍 지정에 사용된다                                                                                                                   |
|  3  | {URI}    | Controller 에 매핑될 URI 주소                                                                                                                        |
|  .  | --get, --post, --put, <br/>--patch, --delete  | HTTP 메서드 지정                                                                                                                                    |
| . | --rest | (Optional) Rest Controller 생성시 지정한다                                                                                                            
| . | --pageable | (Optional) GET 방식일 경우, Controller 메서드에 pageable 인자를 추가한다.                                                                                      |
| . | --accept-form, <br/>--accept-json | (Optional) POST 방식일 경우, 수용 content-type 을 지정한다.<br/>--accept-form: application/x-www-form-urlencoded (기본값)<br/>--accept-json: application/json |                                  
| . | --clear | (Optional) {생성 경로}를 생성하기 전에 삭제를 먼저 수행한다 

Get Controller 
```shell
./web.sh ../admin/src/main/java/app/module/admin/usecase/user/test/adapters/web GetUsers /settings/users --get
```

Get Controller (with Pageable)
```shell
./web.sh ../admin/src/main/java/app/module/admin/usecase/user/test/adapters/web GetUsers /settings/users --get --pageable
```

Post Controller
```shell
./web.sh ../admin/src/main/java/app/module/admin/usecase/user/test/adapters/web CreateUser /settings/users/create --post
```