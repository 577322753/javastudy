package com.base.reflect;

/**
 * ProjectName: JvmStudy
 * ClassName: ClassDemoOne
 * PackageName: com.com.base.reflect
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-30 10:45
 */
public class ClassDemoOne {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //Dog 的实例对象如何表示
        Dog dog = new Dog();
        //Dog 类本身也是一个实例对象，Class类的实例对象

        //第一种方式，根据类直接获取类类型
        Class c1 = Dog.class;
        //第二种方式，通过类的实例对象，获取类类型
        Class c2 = dog.getClass();
        //第三种表达方式,根据全路径类名，来获取
        Class c3 = Class.forName("com.base.reflect.Dog");
        //一个类只可能是Class类的一个实例对象，因此c1 == c2 == c3
        System.out.println(c1 == c2);
        System.out.println(c1 == c3);
        System.out.println(c3 == c2);

        //可以通过类类型来创建类的实例对象,会抛出异常,必须有无参数的构造方法
        Dog dog2 = (Dog) c1.newInstance();
        dog2.bark();

        //可以获取出类的名称 getName带包名，getSimpleName不带包名
        System.out.println(c1.getName());
        System.out.println(c1.getSimpleName());
        ClassUtil.printClassMessage(dog2);
    }
}

class Dog{
    private String name;
    private String old;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public void bark(){
        System.out.println("汪 汪 汪");
    }
}
