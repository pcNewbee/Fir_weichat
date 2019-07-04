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
	//��ͼƬ�Ӹ������ʼ�
	//public static MimeMessage createMimeMess(Session session,String send,String receive,String CCreceive,String BCCreceive) throws MessagingException, IOException {
	public static MimeMessage createMimeMess(Session session,String send,String receive) throws MessagingException, IOException {	
		MimeMessage message=new MimeMessage(session);
		//�ʼ������⡢���ġ��ա������ˣ�������ͼƬ��
		Address address=new InternetAddress(send,"cpcWeb","utf-8");//ѡ��Address�������ࣩ������
		message.setFrom(address);
		message.setSubject("this is the titel(����ͼƬ�Ӹ���)","utf-8");
		//message.setContent("�����������ݡ�����","utf-8");
		//message.setContent("�����������ݡ�����","text/html;charset=utf-8");
		
		//����ͼƬ�ڵ�
		MimeBodyPart imagePart=new MimeBodyPart();
		DataHandler imageDataHandler=new DataHandler(new FileDataSource("src/֤����.jpg"));
		//DataHandler imageDataHandler=new DataHandler(new FileDataSource("E:/picture/֤����.jpg"));/���ܽ����ʼ�/src/֤����.jpg
		imagePart.setDataHandler(imageDataHandler);
		imagePart.setContentID("myIDpic");
		
		//�����ı��ڵ㣬Ŀ����Ϊ�˼���ͼƬ�ڵ�
		MimeBodyPart textPart=new MimeBodyPart();
		//textPart.setContent("image:<image src='֤����.jpg'/>","text/html;charset=utf-8");
		textPart.setContent("image:<img src='cid:myIDpic'/>","text/html;charset=UTF-8");
		
		//��ͼƬ�ڵ���ı��ڵ����
		MimeMultipart mm_text_image=new MimeMultipart();
		mm_text_image.addBodyPart(imagePart);
		mm_text_image.addBodyPart(textPart);
		mm_text_image.setSubType("related");//ͼƬ�����ĵĹ�ϵ
		
		//ע�⣺�����в��ܳ�����Ͻڵ�MimeMultipart--��MimeBodyPart
		MimeBodyPart  text_image_bodyPart=new MimeBodyPart();
		text_image_bodyPart.setContent(mm_text_image);
		
		//����(��Ҫ�ļ���)
		MimeBodyPart attachment=new MimeBodyPart();
		DataHandler fileDataHandler =new DataHandler(new FileDataSource("src/aliyun.txt"));
		//DataHandler fileDataHandler =new DataHandler(new FileDataSource("E:/picture/aliyun.txt"));
		attachment.setDataHandler(fileDataHandler);
		attachment.setFileName(MimeUtility.encodeText(fileDataHandler.getName()));
		
		//����������ĺ͸���
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
