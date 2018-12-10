package com.base.reflect.office;

/**
 * ProjectName: JvmStudy
 * ClassName: Office
 * PackageName: com.com.base.reflect.office
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-30 11:11
 */
public class Office{
    public static void main(String[] args) {
        //使用new方法来创建对象，是静态加载类，在编译时刻就需要加载所有可能需要用到的类
        if("Word".equals(args[0])){
            Word w = new Word();
            w.start();
        }

        if("Excel".equals(args[0])){
            Excel e = new Excel();
            e.start();
        }
    }
}
