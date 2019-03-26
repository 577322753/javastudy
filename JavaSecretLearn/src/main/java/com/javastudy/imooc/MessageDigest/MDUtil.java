package com.javastudy.imooc.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @PackageName com.javastudy.imooc.MessageDigest
 * @Classname MDUtil
 * @Date 19-3-26 下午7:08
 * @Created by wangjingbiao
 * @Description md2、md4、md5加密算法的使用
 */
public class MDUtil {
    public static String jdkMD5(byte[] srcByte) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(srcByte);
        return Hex.encodeHexString(digest);
    }

    public static String jdkMD2(byte[] srcByte) throws NoSuchAlgorithmException {
        MessageDigest md2 = MessageDigest.getInstance("MD2");
        byte[] digest = md2.digest(srcByte);
        return Hex.encodeHexString(digest);
    }


    public static String bcMD4(byte[] srcByte) throws NoSuchAlgorithmException {
        Digest digest = new MD4Digest();
        digest.update(srcByte,0,srcByte.length);
        byte[] md4Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md4Bytes,0);
        return Hex.encodeHexString(md4Bytes);
    }

    public static String bcMD5(byte[] srcByte) throws NoSuchAlgorithmException {
        Digest digest = new MD5Digest();
        digest.update(srcByte,0,srcByte.length);
        byte[] md5Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md5Bytes,0);
        return Hex.encodeHexString(md5Bytes);
    }

    public static String ccMD5(byte[] srcByte){
        return DigestUtils.md5Hex(srcByte);
    }

    public static String ccMD2(byte[] srcByte){
        return DigestUtils.md2Hex(srcByte);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String source="i am hahahaha this is my";
        String result = jdkMD5(source.getBytes());
        String result2 = jdkMD2(source.getBytes());
        String result3 = bcMD4(source.getBytes());
        String result4 = bcMD5(source.getBytes());
        String result5 = ccMD5(source.getBytes());
        String result6 = ccMD2(source.getBytes());
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
        System.out.println(result6);
    }
}
