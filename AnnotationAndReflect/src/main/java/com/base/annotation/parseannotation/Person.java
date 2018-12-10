package com.base.annotation.parseannotation;

/**
 * ProjectName: JvmStudy
 * ClassName: Person
 * PackageName: com.com.base.annotation.parseannotation
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 09:47
 */
@Myann("I am a class")
public class Person {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
    @Myann("i am set name")
    public void setName(String name) {
        this.name = name;
    }
}
