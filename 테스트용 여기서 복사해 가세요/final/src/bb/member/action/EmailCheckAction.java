package bb.member.action;



import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.board.action2.CommandAction;
import bb.member.model.MemberDao;


public class EmailCheckAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		MemberDao dbPro = MemberDao.getInstance();
		String email = request.getParameter("email");
		String returnU =null;
		int check = dbPro.MemberEmailCheck(email);
		String authNum="";
	
		
		request.setAttribute("email", email);
		
		
		if(check==1) {//중복일때
		returnU= "/bbmember/emailCheckEnd.jsp";
		}else if(check==0){//이메일기록이없을때
			HttpSession session = request.getSession(true);//세션이 있는지 없는지를 확인 
			//있으면 세션을 반환시키고. 없으면 생성
			authNum=RandomNum();
			sendEmail(email.toString(), authNum);
			session.setAttribute("authNum", authNum);
			returnU= "/bbmember/emailCheckSend.jsp";
		}
		return returnU;
	}
	
	public String RandomNum() {
		StringBuffer buffer = new StringBuffer();
		for(int i = 0 ; i <= 6 ; i ++) {
			int n = (int) (Math.random()*10);
			buffer.append(n);
		}
		return buffer.toString();
	}
	
	public void sendEmail(String email, String authNum) {
		String host="smtp.gmail.com";//smtp서버
		String subject = "BB사이트 인증번호";
		String fromName= "BB사이트 관리자";
		String from="bbtest1197@gmail.com";//보내는사람 메일
		String to1=email;
		
		String content = "인증번호 ["+authNum+"]";
				
		try {
			Properties props = new Properties();
			//GMail SMTP사용시
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth",  "true");
			//비밀번호 인증 프로토콜
			Session mailSession = Session.getInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("bbtest1197@gmail.com","q1w2e3r4t5!");
			}
			});
		Message msg = new MimeMessage(mailSession);
		msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(
				fromName,"UTF-8","B")));//보내는 사람 설정
		InternetAddress[] address1 = {new InternetAddress(to1)};
		msg.setRecipients(Message.RecipientType.TO, address1);//받는사람 설정1
		msg.setSubject(subject);//제목 설정
		msg.setSentDate(new java.util.Date());//보내는 날짜 설정
		msg.setContent(content,"text/html;charset=euc-kr");//내용설정(html형식)
		
		Transport.send(msg);//메일보내기
	}catch(MessagingException e) {
			e.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
}

}
