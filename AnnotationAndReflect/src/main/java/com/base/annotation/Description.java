package com.base.annotation;

import java.lang.annotation.*;

/**
 * ProjectName: JvmStudy
 * ClassName: Description
 * PackageName: com.com.base.annotation
 * Description:自定义注解
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 09:34
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {
    String desc();
    String author();
    int age() default 18;
}
