package com.book.lambda.filterApple.demo02;

import com.book.lambda.filterApple.Apple;

/**
 * ProjectName: JavaStudy
 * ClassName: ApplePredicate
 * PackageName: com.book.lambda.filterApple.demo02
 * Description: 统一比较接口
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 16:30
 */
public interface ApplePredicate {
    boolean test(Apple apple);
}
