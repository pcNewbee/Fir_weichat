package com.pc.encrypt;

import java.util.Scanner;

public class SecurityDemo {
	static String uname;
	static String pwd;
	static Scanner input=new Scanner(System.in);
	
	public static boolean register() {
		boolean flag=false;
		try {
			System.out.println("�������û�����");
			uname=input.next();
			System.out.println("�������û����룺");
			pwd=input.next();//���ܺ������
			pwd=exclusive.md5Encode(pwd.getBytes());
			System.out.println("ע����Ϣ���£�uname:"+uname+" ���룺"+pwd);
			flag=true;
	}catch(Exception e){
		e.printStackTrace();
	}
		return flag;
	}
	public static boolean login() {
		boolean result=false;
		System.out.println("�������û�����");
		String login_uname=input.next();
		System.out.println("�������û����룺");
		String login_pwd=input.next();
		login_pwd=exclusive.md5Encode(login_pwd.getBytes());
		System.out.println("��½��Ϣ���£�login_uname:"+login_uname+" ���룺"+login_pwd);
		if(login_uname.equals(uname)&&login_pwd.equals(pwd)) {
			result=true;
		}
		return result;
	}
	
	public static void main(String[] args) {
		register();
		boolean result=login();
		if(result) {
		System.out.println("��½�ɹ�������");	
		}else {
		System.out.println("��½ʧ�ܣ�����");
		}
	}
	
}
