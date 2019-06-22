package com.javastudy.imooc.base64;

import com.xdja.cssp.sm2cipher.sm2.ISM2Cipher;
import com.xdja.cssp.sm2cipher.sm2.cipher.ConvertUtil;
import com.xdja.cssp.sm2cipher.sm2.cipher.SM2CipherImpl;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * 签名工具类
 * @author 谢文超
 * 提供SHA1WithRSA和SM3WITHSM2签名验证方法
 *
 */
public class SignUtils {
	
	
	/**
	 * 
	 * 方法描述：使用公钥验证签名数据
	 * @author: 谢文超
	 * @date: 2016-1-20 上午9:41:53
	 * @param alg 1-sha1withrsa 2-sm3withsm3
	 * @param publicKey	公钥
	 * @param data	签名原文
	 * @param signature	签名数据
	 * @return true 验证通过 false 验证未通过
	 * @throws Exception
	 */
	public static boolean verifySignByKey(int alg, PublicKey publicKey,
			byte[] data, byte[] signature) {
		try {
			if (ALG_TYPE.rsa.value==alg) {
				return rsaVerifySignByKey(publicKey, data, signature);
			}
			return sm2VerifySignByKey(publicKey, data, signature);
		} catch (Exception e) {
			return false;
		}
		
	}
	
	
	/**
	 * 
	 * 方法描述：使用私钥签名
	 * @author: 谢文超
	 * @date: 2016-1-20 上午9:41:53
	 * @param alg 1-sha1withrsa 2-sm3withsm3
	 * @param privateKey	私钥
	 * @param data	签名原文
	 * @return signature	签名数据
	 * @throws Exception
	 */
	public static  byte[] signByKey(int alg, PrivateKey privateKey,
			byte[] data) {
		try {
			if (ALG_TYPE.rsa.value==alg) {
				return rsaSign(privateKey, data);
			}
			return sm2Sign(privateKey, data);
		} catch (Exception e) {
			return null;
		}
		
	}
	

	/**
	 * 
	 * 方法描述：使用私钥数据进行签名
	 * @author: 谢文超
	 * @date: 2016-1-20 上午9:41:53
	 * @param alg 1-sha1withrsa 2-sm3withsm3
	 * @param basePriKey	私钥
	 * @param data	签名原文
	 * @return signature	签名数据
	 * @throws Exception
	 */
	public static  byte[] signByKey(int alg, String basePriKey,
			byte[] data) {
		try {
			if (ALG_TYPE.rsa.value==alg) {
				throw new IllegalArgumentException("unsupport sign alg SHA-1WITHRSA");
//				return rsaSign(privateKey, data);
			}
			return sm2SignByKeyData(basePriKey, data);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * 
	 * 方法描述：使用公钥数据验证签名
	 * @author: 谢文超
	 * @date: 2016-1-20 上午9:41:53
	 * @param alg 1-sha1withrsa 2-sm3withsm3
	 * @param publicKey	公钥数据Base64编码
	 * @param data	签名原文
	 * @param signature	签名数据
	 * @return true 验证通过 false 验证未通过
	 * @throws Exception
	 */
	public static boolean verifySignByKeyData(int alg, String publicKey,
			byte[] data, byte[] signature) {
		try {
			if (ALG_TYPE.rsa.value==alg) {
				return rsaVerifySignByKeyData(publicKey, data, signature);
			}
			return sm2VerifySignByKeyData(publicKey, data, signature);
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * 
	 * 方法描述：使用公钥验证签名数据（SHA1WithRSA签名数据）
	 * @author: 谢文超
	 * @date: 2016-1-20 上午9:41:53
	 * @param publicKey	公钥
	 * @param data	签名原文
	 * @param signature	签名数据
	 * @return true 验证通过 false 验证未通过
	 * @throws Exception
	 */
	private static boolean rsaVerifySignByKey(PublicKey publicKey, 
			byte[] data, byte[] signature) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		cipher.update(signature);
		byte[] plainHash = cipher.doFinal();
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		byte[] oriHash = messageDigest.digest(data);
		
		for(int i= 0; i < plainHash.length; i++) {
			if (plainHash[i] != oriHash[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * 方法描述：使用公钥数据验证签名数据（SHA1WithRSA签名数据）
	 * @author: 谢文超
	 * @date: 2016-1-20 上午9:41:53
	 * @param keyData	公钥modulusBase64
	 * @param data	签名原文
	 * @param signature	签名数据
	 * @return true 验证通过 false 验证未通过
	 * @throws Exception
	 */
	private static boolean rsaVerifySignByKeyData(String keyData, 
			byte[] data, byte[] signature) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		PublicKey publicKey = getPublickKey(keyData);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		cipher.update(signature);
		byte[] plainHash = cipher.doFinal();
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		byte[] oriHash = messageDigest.digest(data);
		
		for(int i= 0; i < plainHash.length; i++) {
			if (plainHash[i] != oriHash[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * 方法描述：使用sm2算法公钥验证数字签名
	 * @author: 谢文超
	 * @date: 2016-1-20 上午9:41:53
	 * @param publicKey	公钥
	 * @param sign	签名数据
	 * @param original	签名原文
	 * @return  true-通过 false-未通过
	 * @throws IllegalArgumentException
	 */
	private static boolean sm2VerifySignByKey(PublicKey publicKey, byte[] original, byte[] sign) 
			throws IllegalArgumentException {
		if (publicKey == null || original == null || sign == null ||
				original.length <= 0 || sign.length <= 0) {
			throw new IllegalArgumentException("param error: publicKey,sign,original can not be null");
		}
		
		boolean flag;
		ISM2Cipher cipher = new SM2CipherImpl();
		flag = cipher.verify((ECPublicKey)publicKey, sign, original);
		return flag;
	}
	
	/**
	 * 
	 * 方法描述：使用sm2算法公钥验证数字签名
	 * @author: 谢文超
	 * @date: 2016-1-20 上午9:41:53
	 * @param base64Key	公钥
	 * @param sign	签名数据
	 * @param original	签名原文
	 * @return  true-通过 false-未通过
	 * @throws IllegalArgumentException
	 */
	private static boolean  sm2VerifySignByKeyData(String base64Key, byte[] original, byte[] sign) 
			throws IllegalArgumentException {
		if (StringUtils.isBlank(base64Key) || original == null || sign == null ||
				original.length <= 0 || sign.length <= 0) {
			throw new IllegalArgumentException("param error: publicKey,sign,original can not be null");
		}
		
		boolean flag;
		ISM2Cipher cipher = new SM2CipherImpl();
		byte[] rdata = new byte[32];
		byte[] sdata = new byte[32];
		System.arraycopy(sign, 0, rdata, 0, 32);
		System.arraycopy(sign, 32, sdata, 0, 32);
		BigInteger[] rs = new BigInteger[2];
		rs[0] = new BigInteger(1, rdata);
		rs[1] = new BigInteger(1, sdata);
		
		flag = cipher.verify(base64Key, rs, original);
		return flag;
	}
	
	/**  
     * 根据公钥n生成公钥 (e默认65537)
     * @param modulus   公钥n串  Base64
     * @return 返回公钥PublicKey 
     * @throws Exception 
     */  
    private static PublicKey getPublickKey(String modulus)  
            throws Exception {  
        KeySpec publicKeySpec = new RSAPublicKeySpec(  
                new BigInteger(1, Base64.decode(modulus.getBytes())), new BigInteger("65537", 10));
        KeyFactory factory = KeyFactory.getInstance("RSA");
		return factory.generatePublic(publicKeySpec);
    }
    
    /**
	 * 
	 * 方法描述：使用sm2算法私钥签名数据
	 * @author: 谢文超
	 * @date: 2015-5-5 上午11:24:11
	 * @param privateKey	私钥
	 * @param original		签名原文
	 * @return byte[]  签名数据
	 * @throws IllegalArgumentException
	 */
	private static byte[] sm2Sign(PrivateKey privateKey, byte[] original) 
			throws IllegalArgumentException {
		if (privateKey == null || original == null ||
				original.length <= 0) {
			throw new IllegalArgumentException("param error: privateKey,original can not be null");
		}
		ISM2Cipher cipher = new SM2CipherImpl();
	
		return cipher.sign((ECPrivateKey) privateKey, original);
	}
	
	/**
	 * 
	 * 方法描述：使用sm2算法私钥签名数据
	 * @author: 谢文超
	 * @date: 2015-5-5 上午11:24:11
	 * @param base64PriKey	私钥Base64编码字符串
	 * @param original		签名原文
	 * @return byte[]  签名数据
	 * @throws IllegalArgumentException
	 */
	private static byte[] sm2SignByKeyData(String base64PriKey, byte[] original) 
			throws IllegalArgumentException {
		if (base64PriKey == null || original == null ||
				original.length <= 0) {
			throw new IllegalArgumentException("param error: privateKey,original can not be null");
		}
		byte[] result = new byte[64];
		ISM2Cipher cipher = new SM2CipherImpl();
		BigInteger[] rs = cipher.sign(base64PriKey, original);
		byte[] rData = ConvertUtil.hexStringToBytes(rs[0].toString(16));
		System.arraycopy(rData, 
				0, result, 32 - rData.length, rData.length);
		byte[] sData = ConvertUtil.hexStringToBytes(rs[1].toString(16));
		System.arraycopy(sData, 
				0, result, 64 - sData.length, sData.length);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：签名数据（SHA1WithRSA签名数据）
	 * @author: 谢文超
	 * @date: 2015-5-13 上午9:41:53
	 * @param privateKey 私钥
	 * @param data	签名原文
	 * @return 签名数据
	 * @throws Exception
	 */
	private static byte[] rsaSign(PrivateKey privateKey, 
			byte[] data) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		messageDigest.update(data);
		byte[] digestData = messageDigest.digest();
	
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(digestData);
	}
}
