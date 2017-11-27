package member.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Session;
import board.action.CommandAction;
import member.model.MemberDao;

public class EmailCheckAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		
		MemberDao dbPro = MemberDao.getInstance();
		String email = request.getParameter("email");
		String returnU =null;
		int check = dbPro.MemberEmailCheck(email);
		String authNum="";
		
		request.setAttribute("email", email);
		
		
		if(check==1) {//중복일때
		returnU= "/member/emailCheckEnd.jsp";
		}else if(check==0){//이메일기록이없을때
			authNum=RandomNum();
			sendEmail(email.toString(), authNum);
			returnU= "/member/emailCheckSend.jsp";
		}
		return returnU;
	}
	
	public String RandomNum() {
		
		return "";
	}
	
	public void sendEmail(String email, String authNum) {
		String host="smtp.gmail.com";//smtp서버
		String subject = "BB사이트 인증번호";
		String fromName= "BB사이트 관리자";
		String from="dksrudgus91@gmail.com";//보내는사람 메일
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
			
			Session mailSession = Session.getInstance(props,)
		}
	}
}
