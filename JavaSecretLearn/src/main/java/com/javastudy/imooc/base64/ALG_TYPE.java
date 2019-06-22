package com.javastudy.imooc.base64;

/**
 * 
 * 
 * @author: 谢文超
 * @date: 2016-01-20 上午11:18:02
 * note:证书算法类型定义
 *
 */
public enum ALG_TYPE {

	/**
	 * 算法类型 1-SM3WITHSM2
	 */
	sm2(1),
	/**
	 * 算法类型 2-SHA-1WITHRSA
	 */
	rsa(2);
	
	public int value;
	
	ALG_TYPE(int value) {
		this.value = value;
	}
	
	public static boolean isExsit(int value) {
		return rsa.value==value || sm2.value==value;
	}
}
