package com.base.reflect.office;

import com.base.reflect.officebetter.OfficeAble;

/**
 * ProjectName: JvmStudy
 * ClassName: Excel
 * PackageName: com.com.base.reflect.office
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-07-30 11:12
 */
public class Excel implements OfficeAble {
    @Override
    public void start(){
        System.out.println("excel is work");
    }
}
