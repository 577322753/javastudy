package com.base.annotation.parseannotation;

import java.lang.annotation.*;

/**
 * ProjectName: JvmStudy
 * ClassName: myann
 * PackageName: com.com.base.annotation
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 09:45
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Myann {
    String value();
}
