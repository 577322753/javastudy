package com.base.reflect.office;

import com.base.reflect.officebetter.OfficeAble;

/**
 * ProjectName: JvmStudy
 * ClassName: Word
 * PackageName: com.com.base.reflect.office
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-30 11:12
 */
public class Word implements OfficeAble {
    @Override
    public void start(){
        System.out.println("word is work");
    }
}
