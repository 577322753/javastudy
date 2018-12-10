package com.book.lambda.filterApple.demo03;

/**
 * ProjectName: JavaStudy
 * ClassName: Predicate
 * PackageName: com.book.lambda.filterApple.demo03
 * Description: 通用的过滤接口
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 17:10
 */
public interface Predicate<T> {
    boolean test(T t);
}
