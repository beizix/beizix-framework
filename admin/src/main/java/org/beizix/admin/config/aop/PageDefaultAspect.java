package org.beizix.admin.config.aop;

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

  @Before("execution(* *(.., @PageDefault (*), ..))")
  public void operate1(JoinPoint joinPoint) {

    Arrays.stream(joinPoint.getArgs())
        .filter(arg -> arg instanceof PageableInput)
        .map(arg -> (PageableInput) arg)
        .findFirst()
        .ifPresent(
            pageableBase -> {
              if (pageableBase.getPageNumber() == null) {

                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();

                Annotation[][] annotations = method.getParameterAnnotations();
                for (Annotation[] annotArr : annotations) {
                  for (Annotation annot : annotArr) {
                    if (annot instanceof PageDefault) {
                      PageDefault pageDefault = (PageDefault) annot;
                      pageableBase
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
