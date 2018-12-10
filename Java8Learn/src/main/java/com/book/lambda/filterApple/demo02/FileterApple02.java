package com.book.lambda.filterApple.demo02;

import com.book.lambda.filterApple.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: JavaStudy
 * ClassName: FileterApple02
 * PackageName: com.book.lambda.filterApple.demo02
 * Description: 用接口作为参数，调用的时候通过实现不同的规范，即可过滤出想要的结果
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 16:35
 */
public class FileterApple02 {
    public static List<Apple> filterApple(List<Apple> inventory,ApplePredicate predicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple :inventory) {
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    // 测试方法
    public static void main(String[] args) {
        List<Apple> myApple = new ArrayList<Apple>();
        myApple.add(new Apple("red",170));
        myApple.add(new Apple("green",80));
        myApple.add(new Apple("green",170));

        //得出大于150的苹果
        List<Apple> result1 = filterApple(myApple,new AppleWeightPredicate());
        System.out.println(result1);

        //得出绿色的苹果
        List<Apple> result2 = filterApple(myApple,new AppleGreenColorPredicate());
        System.out.println(result2);

        //得出红色的苹果,利用匿名内部类实现
        List<Apple> result3 = filterApple(myApple, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                if(apple.getColor().equals("red")){
                    return true;
                }
               return false;
            }
        });
        System.out.println(result3);

        //使用Lambda表达式的一种实现，更为优雅
        List<Apple> result4 = filterApple(myApple,(Apple apple) -> apple.getWeight() > 150 );
        System.out.println(result4);
    }
}
