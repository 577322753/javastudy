package com.javastudy.book;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * @PackageName com.javastudy.book
 * @Classname KeyUtils
 * @Date 19-3-28 下午6:56
 * @Created by wangjingbiao
 * @Description 生成key的工具
 */
public class KeyUtils {
    public static byte[] getOneKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static void main(String[] args) throws Exception {
        String key = Hex.encodeHexString(getOneKey());
        System.out.println(key);

    }
}
