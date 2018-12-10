package com.base.reflect;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ProjectName: JvmStudy
 * ClassName: ClassUtil
 * PackageName: com.com.base.reflect
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-30 11:47
 */
public class ClassUtil {
    /**
     * 打印类的信息，包括成员函数、成员变量等信息
     * @param object
     */
    public static void printClassMessage(Object object) {
        Class c = object.getClass();
        System.out.println("类的名称："+c.getName());

        printFieldClassMessage(c);
        printConstructorClassMessage(c);
        printMethodClassMessage(c);


    }

    /**
     * 获取所有的方法信息
     * @param c 类类型
     */
    public static void printMethodClassMessage(Class c) {
        /**
         * getMethods()获取的是所有的public函数，包括父类继承而来的
         * getDeclaredMethods() 获取的是该类自己声明的方法，不问访问权限
         */
        System.out.println(">>>>>>>>>>>>>方法>>>>>>>>>>>>>>");
        Method[] ms = c.getMethods();
        for (int i = 0; i < ms.length; i++) {
            //获取返回值类型，返回的是类类型
            Class returnType = ms[i].getReturnType();
            System.out.print(returnType.getSimpleName()+" ");
            //打印方法名称
            System.out.print(ms[i].getName()+"(");
            //获取参数类型
            Class[] paramTypes=ms[i].getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                System.out.print(paramTypes[j].getSimpleName());
                if(j!=paramTypes.length-1){
                    System.out.print(",");
                }
            }
            System.out.print(")");
            System.out.println();
        }
    }

    /**
     * 获取所有的成员变量的信息
     * @param c
     */
    public static void printFieldClassMessage(Class c) {
        System.out.println(">>>>>>>>>>>>>成员变量>>>>>>>>>>>>>>");
        Field[] fs = c.getDeclaredFields();
        for (Field field : fs) {
            Class fieldType = field.getType();
            String typeName = fieldType.getSimpleName();
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }

    }

    /**
     * 获取构造函数信息
     * @param c
     */
    public static void printConstructorClassMessage(Class c) {
        System.out.println(">>>>>>>>>>>>>构造函数>>>>>>>>>>>>>>");
        Constructor[] cs = c.getDeclaredConstructors();

        for (Constructor constructor : cs) {
            System.out.print(constructor.getName()+"(");
            Class[] paramTypes = constructor.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                System.out.print(paramTypes[j].getSimpleName());
                if(j!=paramTypes.length-1){
                    System.out.print(",");
                }
            }
            System.out.println(")");
        }
    }

}
