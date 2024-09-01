## Application

Pageable 반환 scaffolding 실행
```shell
./application.sh ../core/src/main/java/org/beizix/core/usecase/user/removeRoleWithPrivilege RemoveRoleWithPrivilege --pageable --impl --show
```

List 반환 scaffolding 실행
```shell
./application.sh ../core/src/main/java/org/beizix/core/usecase/user/removeRoleWithPrivilege RemoveRoleWithPrivilege --list --impl --show
```

void scaffolding 실행
```shell
./application.sh ../core/src/main/java/org/beizix/core/usecase/user/removeRoleWithPrivilege RemoveRoleWithPrivilege --void --impl --show
```

기본(Optional) 반환 scaffolding 실행
```shell
./application.sh ../core/src/main/java/org/beizix/core/usecase/user/removeRoleWithPrivilege RemoveRoleWithPrivilege --impl --show
```

## Web

Get Controller 
```shell
./web.sh ../admin/src/main/java/app/module/admin/usecase/user/test/adapters/web GetUsers /settings/users --get --show
```

Get Controller (with Pageable)
```shell
./web.sh ../admin/src/main/java/app/module/admin/usecase/user/test/adapters/web GetUsers /settings/users --get --show --pageable
```

Post Controller
```shell
./web.sh ../admin/src/main/java/app/module/admin/usecase/user/test/adapters/web CreateUser /settings/users/create --post --show
```