package com.amqp.client.testProblem;

/**
 * ProjectName: JavaStudy
 * ClassName: TestSwitch
 * PackageName: com.amqp.client.testProblem
 * Description:对switch结构进行测试
 *
 * @author: wangjingbiao
 * @date: 2018-09-06 14:30
 */
public class TestSwitch {
    public static void main(String[] args) {
        //如果在case后，不加break的话，后面的代码都会执行，只判断了一次case，要避免这一问题，也可利用这个特性。
        int a = 10;
        switch (a) {
            case 1:
                System.out.println("hello");
                break;
            case 10:
                System.out.println("what");
            case 2:
                System.out.println("dododo");
            default:
                System.out.println("i am default");
        }
    }
}
