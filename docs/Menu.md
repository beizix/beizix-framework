# Menu 관리

Menu 는 관리자 - Settings - Menu 관리 화면을 통해 관리됩니다.

Tree View UI 를 통해 부모/자식 노드 관계를 유용하게 적용할 수 있습니다. 

### SEO (검색엔진 최적화)

ogTitle, ogDesc, ogKeywords, ogImage 등 속성값 입력을 통해 현재 메뉴의 meta 및 og 정보가 화면에 노출됩니다. 

```thymeleafexpressions
  <!-- layout.html -->
  
  <title th:text="${currentURI.ogTitle}"></title>
  <meta name="title" th:content="${currentURI.ogTitle}"/>
  <meta name="description" th:content="${currentURI.ogDesc}"/>
  <meta name="keywords" th:content="${currentURI.ogKeywords}"/>
  <meta property="og:title" th:content="${currentURI.ogTitle}"/>
  <meta property="og:description" th:content="${currentURI.ogDesc}"/>
  <meta property="og:keywords" th:content="${currentURI.ogKeywords}"/>
  <meta property="og:image" th:content="${currentURI.ogImage}"/>
```

경우에 따라 특정 페이지에서 SEO 관련 정보를 변경하고 싶을 때가 있습니다.  
예를들어, 제품 상세 페이지는 해당 상품의 고유값에 따라 동적으로 정보가 생성됩니다. 
각 제품별로 SEO 정보를 다르게 보여주고 싶다면 Controller 단에서 변경작업을 해주면 됩니다. 

```java
    // title, seo 속성 변경 예제
    Menu currentURI = ((Menu) request.getAttribute("currentURI")).toBuilder().build();
    currentURI.setOgTitle("modified - exBoardEntity title");
    currentURI.setOgKeywords("modified - exBoardEntity keywords");
    currentURI.setOgDesc("modified - description");
    model.addAttribute("currentURI", currentURI);
```

request 에서 조회된 currentURI 를 기반으로 새로운 Menu 를 생성(toBuilder)한 후 속성을 갱신해 model 에 넣어주면 됩니다.
