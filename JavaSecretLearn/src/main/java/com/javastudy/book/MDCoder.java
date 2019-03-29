package com.javastudy.book;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.Security;

/**
 * @PackageName com.javastudy.book
 * @Classname JdkMDCoder
 * @Date 19-3-28 下午5:18
 * @Created by wangjingbiao
 * @Description Md方法的使用
 * jdk官方目前只支持md5和md2，md4用bc实现
 */
public abstract class MDCoder {
    public static byte[] encodeMD2(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD2");
        return md.digest(data);
    }

    public static byte[] encodeMD5(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(data);
    }

    public static byte[] encodeMD4(byte[] data) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md = MessageDigest.getInstance("MD4");
        return md.digest();
    }

    public static String encodeMd4Hex(byte[] data) throws Exception {
        byte[] b = encodeMD4(data);
        return new String(Hex.encode(b));
    }

}
