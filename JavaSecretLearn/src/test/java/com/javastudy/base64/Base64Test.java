package com.javastudy.base64;

import com.javastudy.imooc.base64.Base64Util;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @PackageName com.javastudy.base64
 * @Classname Base64Test
 * @Date 19-3-28 下午3:32
 * @Created by wangjingbiao
 * @Description base64的基础运算
 */
public class Base64Test {
    @Test
    public void testByte() {
        String str = "密";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println("数组长度：" + bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            byte tmp = bytes[i];
            System.out.println(tmp);
            System.out.printf("%x", bytes[i]);
            System.out.println();
            System.out.printf("%o",bytes[i]);
            System.out.println();
            System.out.println(Integer.toBinaryString(tmp));
        }
        System.out.println();
    }

    @Test
    public void testBase64() {
        String c = Base64Util.bcBase64Encoder("C");
        System.out.println(c);
    }
}
