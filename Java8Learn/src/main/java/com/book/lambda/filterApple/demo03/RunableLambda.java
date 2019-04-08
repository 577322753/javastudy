package com.book.lambda.filterApple.demo03;

/**
 * @PackageName com.book.lambda.filterApple.demo03
 * @Classname RunableLambda
 * @Date 19-4-4 下午4:40
 * @Created by wangjingbiao
 * @Description
 */
public class RunableLambda {
    public static void main(String[] args) {
        System.out.println("before");
        Thread mid = new Thread(() -> System.out.println("a"));
        mid.start();
        System.out.println("after");
    }
}
