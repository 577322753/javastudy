package com.base.reflect.officebetter;

/**
 * ProjectName: JvmStudy
 * ClassName: OfficeBetter
 * PackageName: com.com.base.reflect.officebetter
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-30 11:18
 */
public class OfficeBetter {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //编译时期不会报错,运行时期找不到类才会报错
        Class c = Class.forName(args[0]);
        OfficeAble ab = (OfficeAble) c.newInstance();
        ab.start();
    }
}
