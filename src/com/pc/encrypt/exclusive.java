package com.pc.encrypt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.codec.digest.DigestUtils;

public class exclusive {
    //异或两次实现加密解密
	public static String xor(String input) {
		char[] chr=input.toCharArray();
		for(int i=0;i<chr.length;i++) {
			chr[i]=(char)(chr[i]^3000);
		}
		return  new String(chr);
	}
	
	//MD5加密,输入字节数组，返回字符串；字节数组更加通用
	//不可逆，速度快，不可解
	public static String md5Encode(byte[] input) {
		return DigestUtils.md5Hex(input);
		
	}
	
	//SHA加密，不可解
	//不可逆，安全性高
	public static String sha256Encode(byte[] input) {
		return DigestUtils.sha256Hex(input);
		
	}
	
	//Base64加密：任何类型byte[]-->字符串形式String
	public static String base64Encode(byte[] input)  {
		String result=null;
		try {
			Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method=clazz.getMethod("encode", byte[].class);
			result=(String)method.invoke(null, input);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		}catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//解密：字符串形式String-->任何类型byte[]
	public static byte[]  base64Decode(String input)  {
		byte[] result=null;
		try {
			Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method=clazz.getMethod("decode", String.class);
			result=(byte[])method.invoke(null, input);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		}catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public static void main(String[] args) {
//		String str="hello encrypt";
//		str=xor(str);
//		System.out.println(str);//快捷键"alt+/"
//		str=xor(str);
//		System.out.println(str);
		//String str="hello encrypt";
		String str="hello";
//		str=md5Encode(str.getBytes());
//		System.out.println(str);
		//207fd7d3e577a71681f8a519bdfb568e  十六进制字符串
		//str=sha256Encode(str.getBytes());
		//System.out.println(str);
		//b724751a5c7809b4de63a76c3ef3b7775951f651d6c41c34f62a26a0e50e4ccd  十六进制字符串
		
		//base64加密、解密
		str=base64Encode(str.getBytes());
		System.out.println(str);
		byte[] result=base64Decode(str);
		System.out.println(new String(result));	
	}
}
