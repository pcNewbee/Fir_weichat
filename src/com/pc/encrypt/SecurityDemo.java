package com.pc.encrypt;

import java.util.Scanner;

public class SecurityDemo {
	static String uname;
	static String pwd;
	static Scanner input=new Scanner(System.in);
	
	public static boolean register() {
		boolean flag=false;
		try {
			System.out.println("请输入用户名：");
			uname=input.next();
			System.out.println("请输入用户密码：");
			pwd=input.next();//加密后的密码
			pwd=exclusive.md5Encode(pwd.getBytes());
			System.out.println("注册信息如下：uname:"+uname+" 密码："+pwd);
			flag=true;
	}catch(Exception e){
		e.printStackTrace();
	}
		return flag;
	}
	public static boolean login() {
		boolean result=false;
		System.out.println("请输入用户名：");
		String login_uname=input.next();
		System.out.println("请输入用户密码：");
		String login_pwd=input.next();
		login_pwd=exclusive.md5Encode(login_pwd.getBytes());
		System.out.println("登陆信息如下：login_uname:"+login_uname+" 密码："+login_pwd);
		if(login_uname.equals(uname)&&login_pwd.equals(pwd)) {
			result=true;
		}
		return result;
	}
	
	public static void main(String[] args) {
		register();
		boolean result=login();
		if(result) {
		System.out.println("登陆成功！！！");	
		}else {
		System.out.println("登陆失败！！！");
		}
	}
	
}
