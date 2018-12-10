package com.book.lambda.ApiUse;

/**
 * ProjectName: JavaStudy
 * ClassName: ThreadLambda
 * PackageName: com.book.lambda.ApiUse
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-08-01 08:36
 */
public class ThreadLambda {
    public static void main(String[] args) {
        //匿名内部类创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("what is the fuck,i am thread 1");
            }
        }).start();

        //lambda表达式创建线程
        new Thread(()->{
            System.out.println("what is the fuck,i am thread 2");
        }).start();

        System.out.println("what is the fuck,i am main");
    }
}
