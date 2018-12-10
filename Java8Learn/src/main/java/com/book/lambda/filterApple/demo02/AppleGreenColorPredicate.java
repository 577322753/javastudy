package com.book.lambda.filterApple.demo02;

import com.book.lambda.filterApple.Apple;

/**
 * ProjectName: JavaStudy
 * ClassName: AppleGreenColorPredicate
 * PackageName: com.book.lambda.filterApple.demo02
 * Description: 过了出绿颜色的苹果
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 16:33
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        if(apple.getColor().equals("green")){
            return true;
        }
        return false;
    }
}
