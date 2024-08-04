`beizix` 는 *Robert C. Martin* 의 [도메인 중심 설계 철학](https://medium.com/unil-ci-software-engineering/clean-domain-driven-design-2236f5430a05)과 *Alistair Cockburn* 의 [헥사고날(포트 앤 어댑터)](https://engineering.linecorp.com/ko/blog/port-and-adapter-architecture) 아키텍처를 기반으로 만든 **웹 개발 프레임워크**입니다. 

기초를 뜻하는 단어 `Basics` 에서 착안된 이름이며, 오랜 기간 SI 프로젝트를 수행하며 얻는 여러 경험들을 공유할 수 있는 기본 틀을 만들자는 취지로 시작되었습니다.  

`beizix` 를 하나의 작은 길잡이로 삼아 여러분의 경험을 확장하여 견고한 어플리케이션이 만들어지는데 도움이 되길 바랍니다.

`beizix`는 기본을 뜻하는 단어 `Basics`에서 오랜 기간 SI 프로젝트를 수행하며 얻는 여러 경험들을 기반으로 만들어진 **웹 개발 프레임워크**입니다.

어떤 요구사항에도 적용될 수 있도록 특정 기능과 목적에 종속되지 않은 일반적이고 범용적인 핵심기능을 제공하는데 목적이 있습니다. 


## JPA Metamodel 도입 

JPA를 이용할 때 엔터티 클래스와 해당 속성을 참조해야 합니다.

이를 수행하는 유일한 방법은 속성 이름을 문자열로 제공하는 것입니다. 하지만 여기에는 큰 단점이 있습니다.

프로젝트 진행과정에서 열 이름이 변경되는 경우, 예전 이름을 참조하는 코드가 있어도 컴파일러는 이를 인지하지 못합니다. 즉, type safe 하지 않습니다. 

이 경우 이름이 사용되는 곳을 찾아 수작업으로 변경해주어야 합니다. 만약 이를 놓친 경우, runtime 과정에서 예상치 못한 에러를 만나게 됩니다.

JPA 메타모델은 이러한 단점을 피하고 관리되는 엔터티 클래스의 메타데이터에 대한 정적 액세스를 제공하기 위해 도입되었습니다.

`hibernate-jpamodelgen` maven 모듈을 통해 `target/generated-sources/annotations` 로 정적 JPA Metamodel 클래스들이 생성됩니다.

```
$ tree core/target/generated-sources/annotations/
core/target/generated-sources/annotations/
`-- org
    `-- beizix
        `-- core
            `-- config
                `-- adapter
                    `-- persistence
                        `-- entity
                            |-- ExBoardAttachment_.java
                            |-- ExBoard_.java
                            |-- FrontUser_.java
                            |-- LoggedInUserEmbeddable_.java
                            ...
```
JPA 사양에 따라 생성된 클래스는 해당 엔티티 클래스와 동일한 패키지에 상주하며 끝에 "_"(언더스코어)가 추가된 동일한 이름을 갖습니다.

이제 Metamodel 클래스를 이용해 type safe 프로그래밍을 해봅시다.

```java
// Metamodel 적용 전
@OneToMany(
        mappedBy = "exBoard",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
private Set<ExBoardAttachment> attachments;

// Metamodel 적용 후
@OneToMany(
        mappedBy = ExBoardAttachment_.EX_BOARD,
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
private Set<ExBoardAttachment> attachments;
```

정적 Metamodel 클래스는 maven compile 단계에서 생성되며 IDE 에서 어플리케이션 구동 시 빌드과정에서 자동으로 수행됩니다.

생성된 모델을 참조하기 위해서는 `target/generated-sources` 경로가 IDE 의 **classpath** 에 추가되어야 합니다.

인텔리제이의 경우, File > Project Structure > Modules > target\generated-sources 우클릭 후 Sources 체크 해주면 됩니다.

