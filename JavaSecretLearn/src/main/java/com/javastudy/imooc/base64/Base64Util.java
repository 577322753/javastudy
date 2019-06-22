package com.javastudy.imooc.base64;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.openssl.PEMParser;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @PackageName com.javastudy.imooc.base64
 * @Classname Base64Util
 * @Date 19-3-26 下午6:03
 * @Created by wangjingbiao
 * @Description 使用jdk、bc、cc分别实现base64的转换
 */
public class Base64Util {
    /**
     * jdk自带方式实现base64编码
     * @param src
     * @return
     */
    public static String jdkBase64Encoder(String src) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(src.getBytes());
    }

    /**
     * jdk自带方式实现base64解码
     * @param base64Str
     * @return
     *
     * @throws IOException
     */
    public static byte[] jdkBase64Decoder(String base64Str) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Str);
    }

    /**
     * bc 外部扩展包实现base64编码
     * @param src
     * @return
     */
    public static String bcBase64Encoder(String src) {
        byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        return new String(encodeBytes);
    }

    /**
     * bc 外部扩展包实现base64解码
     * @param src
     * @return
     */
    public static byte[] bcBase64Decoder(String src) {
        byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(src);
        return decodeBytes;
    }

    /**
     * cc 外部扩展包实现base64编码
     * @param src
     * @return
     */
    public static String ccBase64Encoder(String src) {
        byte[] bytes = Base64.encodeBase64(src.getBytes());
        return new String(bytes);
    }

    /**
     * cc 外部扩展包实现base64解码
     * @param base64Str
     * @return
     */
    public static byte[] ccBase64Decoder(String base64Str) {
        return Base64.decodeBase64(base64Str);
    }

    /**
     * 测试主类
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String str = "i am wang,you can call me father wang";
        String base64Result = jdkBase64Encoder(str);
        String decoderResult = new String(jdkBase64Decoder(base64Result));
        System.out.println(base64Result + " : " + decoderResult);
        String base64Result2 = ccBase64Encoder(str);
        String decoderResult2 = new String(ccBase64Decoder(base64Result));
        System.out.println(base64Result2 + " : " + decoderResult2);
        String base64Result3 =bcBase64Encoder(str);
        String decoderResult3 = new String(bcBase64Decoder(base64Result));
        System.out.println(base64Result3 + " : " + decoderResult3);
    }
}
