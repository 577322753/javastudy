package com.book.lambda.filterApple.demo02;

import com.book.lambda.filterApple.Apple;

/**
 * ProjectName: JavaStudy
 * ClassName: AppleWeightPredicate
 * PackageName: com.book.lambda.filterApple.demo02
 * Description: 过滤出大于150的苹果
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 16:32
 */
public class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        if(apple.getWeight()> 150){
            return true;
        }
        return false;
    }
}
