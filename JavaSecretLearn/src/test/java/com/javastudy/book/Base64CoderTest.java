package com.javastudy.book;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @PackageName com.javastudy.book
 * @Classname Base64CoderTest
 * @Date 19-3-28 下午4:38
 * @Created by wangjingbiao
 * @Description
 */
public class Base64CoderTest {
    @Test
    public void testBase64() throws Exception {
        String inputStr = "我的中国心a";
        System.out.println("原文：" + inputStr);
        String encode = Base64Coder.encode(inputStr);
        String encode2 = Base64Coder.encodeSafe(inputStr);
        System.out.println("base64BC： " + encode);
        System.out.println("base64CC： "+ encode2);
        String decode = Base64Coder.decode(encode);
        System.out.println(decode);
    }
}