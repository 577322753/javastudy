package com.book.lambda.ApiUse;

import com.book.lambda.filterApple.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * ProjectName: JavaStudy
 * ClassName: CompareDemo
 * PackageName: com.book.lambda.ApiUse
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 17:35
 */
public class CompareDemo {
    public static void main(String[] args) {
        List<Apple> myApple = new ArrayList<Apple>();
        myApple.add(new Apple("red", 170));
        myApple.add(new Apple("green", 80));
        myApple.add(new Apple("green", 170));

        myApple.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return new Double(o1.getWeight()).compareTo(new Double(o2.getWeight()));
            }
        });
        System.out.println(myApple);

        //lambda表达式来进行排序
        myApple.sort((Apple a1,Apple a2) -> new Double(a1.getWeight()).compareTo(new Double(a2.getWeight())));
        System.out.println(myApple);
    }
}
