package com.pc.javamail;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class javamailDemoPlus {
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
	//带图片加附件的邮件
	//public static MimeMessage createMimeMess(Session session,String send,String receive,String CCreceive,String BCCreceive) throws MessagingException, IOException {
	public static MimeMessage createMimeMess(Session session,String send,String receive) throws MessagingException, IOException {	
		MimeMessage message=new MimeMessage(session);
		//邮件：标题、正文、收、发件人（附件、图片）
		Address address=new InternetAddress(send,"cpcWeb","utf-8");//选择Address（抽象类）的子类
		message.setFrom(address);
		message.setSubject("this is the titel(含有图片加附件)","utf-8");
		//message.setContent("这是正文内容。。。","utf-8");
		//message.setContent("这是正文内容。。。","text/html;charset=utf-8");
		
		//创建图片节点
		MimeBodyPart imagePart=new MimeBodyPart();
		DataHandler imageDataHandler=new DataHandler(new FileDataSource("src/证件照.jpg"));
		//DataHandler imageDataHandler=new DataHandler(new FileDataSource("E:/picture/证件照.jpg"));/加密解密邮件/src/证件照.jpg
		imagePart.setDataHandler(imageDataHandler);
		imagePart.setContentID("myIDpic");
		
		//创建文本节点，目的是为了加载图片节点
		MimeBodyPart textPart=new MimeBodyPart();
		//textPart.setContent("image:<image src='证件照.jpg'/>","text/html;charset=utf-8");
		textPart.setContent("image:<img src='cid:myIDpic'/>","text/html;charset=UTF-8");
		
		//将图片节点和文本节点组合
		MimeMultipart mm_text_image=new MimeMultipart();
		mm_text_image.addBodyPart(imagePart);
		mm_text_image.addBodyPart(textPart);
		mm_text_image.setSubType("related");//图片与正文的关系
		
		//注意：正文中不能出现组合节点MimeMultipart--》MimeBodyPart
		MimeBodyPart  text_image_bodyPart=new MimeBodyPart();
		text_image_bodyPart.setContent(mm_text_image);
		
		//附件(需要文件名)
		MimeBodyPart attachment=new MimeBodyPart();
		DataHandler fileDataHandler =new DataHandler(new FileDataSource("src/aliyun.txt"));
		//DataHandler fileDataHandler =new DataHandler(new FileDataSource("E:/picture/aliyun.txt"));
		attachment.setDataHandler(fileDataHandler);
		attachment.setFileName(MimeUtility.encodeText(fileDataHandler.getName()));
		
		//重新组和正文和附件
		MimeMultipart mm=new MimeMultipart();
		mm.addBodyPart(text_image_bodyPart);
		mm.addBodyPart(attachment);
		mm.setSubType("mixed");
		
		
		//message.setContent(mm,"charset=utf-8");
		//message.setContent(mm,"multipart/mixed");
		message.setContent(mm);
		//message.setContent(mm,"multipart/alternative;charset=utf-8");
		//message.setContent(mm,"text/html;charset=utf-8");
		//message.setContent(mm,"multipart/mixed;charset=utf-8");
		//message.setContent(mm, "utf-8");
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
