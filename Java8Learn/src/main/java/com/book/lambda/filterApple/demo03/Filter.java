package com.book.lambda.filterApple.demo03;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: JavaStudy
 * ClassName: Filter
 * PackageName: com.book.lambda.filterApple.demo03
 * Description: 更加通用的过滤方法
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 17:11
 */
public class Filter<T> {
   public List<T> filter(List<T> list,Predicate<T> p) {
       List<T> result = new ArrayList<>();
       for (T e : list) {
           if (p.test(e)) {
               result.add(e);
           }
       }
       return result;
   }
}
