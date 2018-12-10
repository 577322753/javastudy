package com.base.annotation;

/**
 * ProjectName: JvmStudy
 * ClassName: User
 * PackageName: com.com.base.annotation
 * Description:使用自定义注解
 *
 * @author: wangjingbiao
 * @date: 2018-07-31 09:37
 */
public class User {
    @Description(desc = "i am eyeColor",author = "wangjingbiao",age = 22)
    public String eyeColor(){
      return "red";
    };
}
