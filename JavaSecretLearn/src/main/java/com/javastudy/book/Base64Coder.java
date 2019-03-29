package com.javastudy.book;

import org.bouncycastle.util.encoders.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @PackageName com.javastudy.book
 * @Classname Base64Coder
 * @Date 19-3-28 下午4:32
 * @Created by wangjingbiao
 * @Description bc实现base64编码，bc不遵循RFC2045规定的每76个字符增加一个回车换行符号\r\n
 */
public abstract class Base64Coder {
    /**
     * 字符编码
     */
    public static final String ENCODING = "UTF-8";

    /**
     * 将普通字符转换成base64字符串
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(String data) throws Exception {
        byte[] b = Base64.encode(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

    /**
     * cc中的规范，行末会增加回车换行符号
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeSafe(String data) throws UnsupportedEncodingException {
        byte[] b = org.apache.commons.codec.binary.Base64.encodeBase64(data.getBytes(ENCODING), true);
        return new String(b, ENCODING);
    }

    /**
     * 对base64字符串进行解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decode(String data) throws Exception {
        byte[] b = Base64.decode(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

}
