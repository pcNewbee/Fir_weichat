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
	//	Session session=new Session();��sesson����ͬ��jsp�е�
		Properties props=new Properties();
		props.setProperty("mail.transport.protocol", "smtp");//ʹ��Э�飺smtp
		props.setProperty("mail.smtp.host", "smtp.qq.com");//Э���ַ
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.auth", "true");//��Ҫ��Ȩ
		//QQ����ҪSSL��ȫ��֤
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Session session=Session.getInstance(props);
		session.setDebug(true);//������־
		//�����ʼ�
		try {
			MimeMessage message=createMimeMess(session, "1595294329@qq.com","2049089996@qq.com");
			Transport transport=session.getTransport();//�������Ӷ���
			transport.connect("1595294329@qq.com", "hsvwertqanjqghjd");//�������ӣ���������Ȩ��ķ�ʽ
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	}
	//public static MimeMessage createMimeMess(Session session,String send,String receive,String CCreceive,String BCCreceive) throws MessagingException, IOException {
	public static MimeMessage createMimeMess(Session session,String send,String receive) throws MessagingException, IOException {	
		MimeMessage message=new MimeMessage(session);
		//�ʼ������⡢���ġ��ա������ˣ�������ͼƬ��
		Address address=new InternetAddress(send,"cpcWeb","utf-8");//ѡ��Address�������ࣩ������
		message.setFrom(address);
		message.setSubject("this is the titel","utf-8");
		//message.setContent("�����������ݡ�����","utf-8");
		message.setContent("�����������ݡ�����","text/html;charset=utf-8");
		
		//�ռ������ͣ�TO��ͨ�ռ��ˡ�CC���͡�BCC����
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive,"��ͨ�ռ���","utf-8"));
//		message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(CCreceive,"CC�����ռ���","utf-8"));
//		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(BCCreceive,"BCC�����ռ���","utf-8"));
//		
		message.setSentDate(new Date());
		message.saveChanges();
		
		return message;
	}
}
