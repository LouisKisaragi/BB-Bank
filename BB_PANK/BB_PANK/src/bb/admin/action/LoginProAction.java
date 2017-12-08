package bb.admin.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.admin.model.AdminDao;
import bb.admin.model.AdminDto;
import bb.board.action.CommandAction;
import bb.member.model.MemberDto;


public class LoginProAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		//세션생성
		HttpSession session = request.getSession(true);//세션이 있는지 없는지를 확인 
		//있으면 세션을 반환시키고. 없으면 생성
		System.out.println("123");
		String page=request.getParameter("page");
		AdminDto article = new AdminDto();	//데이터를 처리할 빈

		AdminDao dbPro = AdminDao.getInstance(); //DB 연결
		
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		article.setId(request.getParameter("id"));
		article.setPass(request.getParameter("pass"));
		int check = dbPro.loginArticle(id,pass);
		
		
		request.setAttribute("page",page);
		
		if(check==1) {
			AdminDto article1 = dbPro.memberArticle(id,pass);
			System.out.println("super:"+article1.getSuper_m());
			int superm=Integer.parseInt(article1.getSuper_m());
			if(superm==1) {
				String loginid = article1.getId();
				String loginpass = article1.getPass();
				String loginname= article1.getName();
				int point = article1.getPoint();
				String lognick=article1.getNickname();
				int Super_m=Integer.parseInt(article1.getSuper_m());
			

				request.setAttribute("check", check);
			
		
				
			session.setAttribute("logNick",lognick);
			session.setAttribute("logId",loginid);
			session.setAttribute("logPass",loginpass);
			session.setAttribute("logName", loginname);
			session.setAttribute("logPoint", point);
			session.setAttribute("login", 1);
			session.setAttribute("adminId", loginid);
			session.setAttribute("super", 2);
			session.setAttribute("adminNick",lognick);
			session.setAttribute("adminPass",loginpass);
			}
			else {

				request.setAttribute("check", 0);
	
			}
		}
		return "/bbadmin/adloginPro.jsp";	//해당 뷰 경로 반환
	}
}
