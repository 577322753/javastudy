package com.base.annotation.parseannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * ProjectName: JvmStudy
 * ClassName: ParseAnn
 * PackageName: com.com.base.annotation
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 09:44
 */
public class ParseAnn {
    public static void main(String[] args) {
        //1.使用类加载器加载类
        Class c = Child.class;
        //2.判断类上是否存在注解
        if(c.isAnnotationPresent(Myann.class)){
            //3.取出注解并赋值
            Myann myann = (Myann) c.getAnnotation(Myann.class);
            System.out.println(myann.value());
        }
        //4.遍历所有的方法来获取注解
        Method[] methods = c.getMethods();
        for (Method me :methods) {
            if(me.isAnnotationPresent(Myann.class)){
                Myann myann = me.getAnnotation(Myann.class);
                System.out.println(myann.value());
            }
        }
        //4.第二种解析方法
        for (Method me :methods) {
            Annotation[] a = me.getAnnotations();
            for (Annotation ann :a) {
                if(ann instanceof Myann){
                    System.out.println(((Myann) ann).value());
                }
            }
        }
    }
}
