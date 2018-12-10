package com.base.annotation.parseannotation;

/**
 * ProjectName: JvmStudy
 * ClassName: Child
 * PackageName: com.com.base.annotation.parseannotation
 * Description:继承的时候只会继承类上的注解，不会继承方法上的注解
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 10:07
 */
public class Child extends Person {
    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}
