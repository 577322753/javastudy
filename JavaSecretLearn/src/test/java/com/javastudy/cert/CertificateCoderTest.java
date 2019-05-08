package com.javastudy.cert;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @PackageName com.javastudy.cert
 * @Classname CertificateCoderTest
 * @Date 19-4-22 下午7:12
 * @Created by wangjingbiao
 * @Description 测试证书的使用
 */
public class CertificateCoderTest {
    private String password = "123456";
    private String alias = "www.wangjingbiao.com";
    private String certficatePath = "/data/data/testData/cert/wangjingbiao.cer";
    private String keyStorePath = "/data/data/testData/cert/wangjingbiao.keystore";

    /**
     * 公钥加密，私钥解密
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        System.out.println("公钥加密，私钥解密");
        String inputStr = "数字证书";
        byte[] data = inputStr.getBytes();
        byte[] encrypt = CertificateCoder.encryptByPublicKey(data, certficatePath);
        byte[] decrypt = CertificateCoder.decryptByPrivateKey(encrypt, keyStorePath, alias, password);
        String outputStr = new String(decrypt);
        System.out.println("加密前:\n" + inputStr);
        System.out.println("解密后：\n" + outputStr);
        //验证数据一致性
        assertArrayEquals(data, decrypt);
    }

    /**
     * 私钥加密，公钥解密
     */
    @Test
    public void test2() throws Exception {
        System.out.println("私钥加密，公钥解密");
        String inputStr = "数字证书";
        byte[] data = inputStr.getBytes();
        byte[] encodedData = CertificateCoder.encryptByPrivateKey(data, keyStorePath, alias, password);
        byte[] decodedData = CertificateCoder.decryptByPublicKey(encodedData, certficatePath);
        String outputStr = new String(decodedData);
        System.out.println("加密前:\n" + inputStr);
        System.out.println("解密后：\n" + outputStr);
        assertArrayEquals(data, decodedData);
    }

    /**
     * 签名验证
     *
     * @throws Exception
     */
    @Test
    public void testSign() throws Exception {
        String inputStr = "签名";
        byte[] data = inputStr.getBytes();
        System.out.println("私钥签名-公钥验证");
        byte[] sign = CertificateCoder.sign(data, keyStorePath, alias, password);
        System.out.println("签名：\n" + Hex.toHexString(sign));
        boolean status = CertificateCoder.verify(data, sign, certficatePath);
        System.out.println("验证状态: " + status);
        assertTrue(status);

    }
}

