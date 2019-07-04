package com.pc.encrypt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.codec.digest.DigestUtils;

public class exclusive {
    //�������ʵ�ּ��ܽ���
	public static String xor(String input) {
		char[] chr=input.toCharArray();
		for(int i=0;i<chr.length;i++) {
			chr[i]=(char)(chr[i]^3000);
		}
		return  new String(chr);
	}
	
	//MD5����,�����ֽ����飬�����ַ������ֽ��������ͨ��
	//�����棬�ٶȿ죬���ɽ�
	public static String md5Encode(byte[] input) {
		return DigestUtils.md5Hex(input);
		
	}
	
	//SHA���ܣ����ɽ�
	//�����棬��ȫ�Ը�
	public static String sha256Encode(byte[] input) {
		return DigestUtils.sha256Hex(input);
		
	}
	
	//Base64���ܣ��κ�����byte[]-->�ַ�����ʽString
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
	
	//���ܣ��ַ�����ʽString-->�κ�����byte[]
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
//		System.out.println(str);//��ݼ�"alt+/"
//		str=xor(str);
//		System.out.println(str);
		//String str="hello encrypt";
		String str="hello";
//		str=md5Encode(str.getBytes());
//		System.out.println(str);
		//207fd7d3e577a71681f8a519bdfb568e  ʮ�������ַ���
		//str=sha256Encode(str.getBytes());
		//System.out.println(str);
		//b724751a5c7809b4de63a76c3ef3b7775951f651d6c41c34f62a26a0e50e4ccd  ʮ�������ַ���
		
		//base64���ܡ�����
		str=base64Encode(str.getBytes());
		System.out.println(str);
		byte[] result=base64Decode(str);
		System.out.println(new String(result));	
	}
}
