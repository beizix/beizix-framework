package org.beizix.core.configuration.application.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class PageDefaultAspect {

  /**
   * Controller 메서드에 선언된 @PageDefault 어노테이션 속성값을 기반으로 pageableInput 속성을 셋업해준다.
   *
   * @param joinPoint
   */
  @Before("execution(* *(.., @org.beizix.core.configuration.application.aop.PageDefault (*), ..))")
  public void operate1(JoinPoint joinPoint) {

    Arrays.stream(joinPoint.getArgs())
        .filter(arg -> arg instanceof PageableInput)
        .map(arg -> (PageableInput) arg)
        .findFirst()
        .ifPresent(
            pageableInput -> {
              if (pageableInput.getPageNumber() == null) {

                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();

                Annotation[][] annotations = method.getParameterAnnotations();
                for (Annotation[] annotArr : annotations) {
                  for (Annotation annot : annotArr) {
                    if (annot instanceof PageDefault) {
                      PageDefault pageDefault = (PageDefault) annot;
                      pageableInput
                          .setPageNumber(pageDefault.pageNumber())
                          .setPageSize(pageDefault.pageSize())
                          .setOrderBy(pageDefault.orderBy())
                          .setOrderDir(pageDefault.orderDir());
                    }
                  }
                }
              }
            });
  }
}
