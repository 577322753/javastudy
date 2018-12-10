package com.base.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ProjectName: JvmStudy
 * ClassName: InvokeDemo
 * PackageName: com.com.base.reflect
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-30 14:45
 */
public class InvokeDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        A a1 = new A();
        Class c = a1.getClass();
        Method method = c.getDeclaredMethod("print",new Class[]{int.class,int.class});
        Object o = method.invoke(a1,new Object[]{1,2});
    }
}

class A{
    public void print(int a,int b){
        System.out.println(a+b);
    }

    public void print(String a,String b){
        System.out.println(a.toUpperCase() + "," + b.toLowerCase());
    }
}
