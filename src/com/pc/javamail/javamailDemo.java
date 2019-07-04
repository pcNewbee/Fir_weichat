package com.pc.javamail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class javamailDemo {
	public static void main(String[] args) {
	//	Session session=new Session();此sesson不等同于jsp中的
		Properties props=new Properties();
		props.setProperty("mail.transport.protocol", "smtp");//使用协议：smtp
		props.setProperty("mail.smtp.host", "smtp.qq.com");//协议地址
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.auth", "true");//需要授权
		//QQ：需要SSL安全认证
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Session session=Session.getInstance(props);
		session.setDebug(true);//开启日志
		//创建邮件
		try {
			MimeMessage message=createMimeMess(session, "1595294329@qq.com","2049089996@qq.com");
			Transport transport=session.getTransport();//建立连接对象
			transport.connect("1595294329@qq.com", "hsvwertqanjqghjd");//建立连接，密码以授权码的方式
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	}
	//public static MimeMessage createMimeMess(Session session,String send,String receive,String CCreceive,String BCCreceive) throws MessagingException, IOException {
	public static MimeMessage createMimeMess(Session session,String send,String receive) throws MessagingException, IOException {	
		MimeMessage message=new MimeMessage(session);
		//邮件：标题、正文、收、发件人（附件、图片）
		Address address=new InternetAddress(send,"cpcWeb","utf-8");//选择·Address（抽象类）的子类
		message.setFrom(address);
		message.setSubject("this is the titel","utf-8");
		//message.setContent("这是正文内容。。。","utf-8");
		message.setContent("这是正文内容。。。","text/html;charset=utf-8");
		
		//收件人类型：TO普通收件人、CC抄送、BCC密送
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive,"普通收件人","utf-8"));
//		message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(CCreceive,"CC抄送收件人","utf-8"));
//		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(BCCreceive,"BCC密送收件人","utf-8"));
//		
		message.setSentDate(new Date());
		message.saveChanges();
		
		return message;
	}
}
