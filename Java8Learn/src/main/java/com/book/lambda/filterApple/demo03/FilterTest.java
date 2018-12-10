package com.book.lambda.filterApple.demo03;

import com.book.lambda.filterApple.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: JavaStudy
 * ClassName: FilterTest
 * PackageName: com.book.lambda.filterApple.demo03
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 17:19
 */
public class FilterTest {
    public static void main(String[] args) {
        Filter<Apple> filter = new Filter<>();
        List<Apple> myApple = new ArrayList<Apple>();
        myApple.add(new Apple("red", 170));
        myApple.add(new Apple("green", 80));
        myApple.add(new Apple("green", 170));
        List<Apple> apples = filter.filter(myApple, (Apple apple) -> apple.getColor().equals("green"));
        System.out.println(apples);

        Filter<Integer> filter2 = new Filter<>();

        List<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(15);
        integers.add(20);
        integers.add(5);
        List<Integer> integerList = filter2.filter(integers, (Integer num) -> num > 10);
        System.out.println(integerList);

    }
}
