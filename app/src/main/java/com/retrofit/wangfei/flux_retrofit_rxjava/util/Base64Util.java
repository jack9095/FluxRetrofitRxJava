package com.retrofit.wangfei.flux_retrofit_rxjava.util;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Base64加密解密工具类
 * @author fei.wang
 *
 */
public class Base64Util {

	/**
	 * 对String数据进行加密
	 * @param strBase64  需要加密的数据
	 * @param code		编码格式 例如："UTF-8"
	 * @return          返回加密后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Encode(String strBase64,String code) throws UnsupportedEncodingException{
		String enToStr = Base64.encodeToString(strBase64.getBytes(), Base64.DEFAULT);
//		String string = new String(Base64.encode(strBase64.getBytes(code), Base64.DEFAULT));
		return enToStr;
	}
	
	/** 
	 * 对base64加密后的数据进行解密
	 * @param str   需要解密的String数据
	 * @param code  编码格式 例如："UTF-8"
	 * @return  返回解密后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Decode(String str,String code) throws UnsupportedEncodingException{
		String string = new String(Base64.decode(str.getBytes(code), Base64.DEFAULT));
		return string;
	}
	
	
	public static void base64Test(){
		// 加密传入的数据是byte类型的，并非使用decode方法将原始数据转二进制，String类型的数据 使用 str.getBytes()即可
		String str = "Hello!";
		// 在这里使用的是encode方式，返回的是byte类型加密数据，可使用new String转为String类型
		String strBase64 = new String(Base64.encode(str.getBytes(), Base64.DEFAULT));
		Log.i("Test", "encode >>>" + strBase64);
		    
		// 这里 encodeToString 则直接将返回String类型的加密数据
		String enToStr = Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
		Log.i("Test", "encodeToString >>> " + enToStr);
		    
		// 对base64加密后的数据进行解密
		Log.i("Test", "decode >>>" + new String(Base64.decode(strBase64.getBytes(), Base64.DEFAULT)));
	}
}
