package org.beizix.admin.config.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.beizix.core.config.enums.OrderDir;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface PageDefault {
  int pageNumber() default 0;

  int pageSize() default 10;

  String orderBy();

  OrderDir orderDir();
}
