package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.action.CommandAction;
import member.model.MemberDao;
import member.model.MemberDto;


public class LoginProAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		//세션생성
		HttpSession session = request.getSession(true);//세션이 있는지 없는지를 확인 
		//있으면 세션을 반환시키고. 없으면 생성

		String page=request.getParameter("page");
		MemberDto article = new MemberDto();	//데이터를 처리할 빈
		
		System.out.println("url::="+page);
		System.out.println("id=="+request.getParameter("id"));
		System.out.println("pass=="+request.getParameter("pass"));
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		article.setId(request.getParameter("id"));
		article.setPass(request.getParameter("pass"));
		MemberDao dbPro = MemberDao.getInstance(); //DB 연결
		int check = dbPro.loginArticle(id,pass);
		request.setAttribute("check", check);
		request.setAttribute("page",page);
		
		if(check==1) {
			MemberDto article1 = dbPro.memberArticle(id,pass);
			String loginid = article1.getId();
			String loginpass = article1.getPass();
			String loginname= article1.getName();
			int point = article1.getPoint();
			String lognick=article1.getNickname();
			
		session.setAttribute("logNick",lognick);
		session.setAttribute("logId",loginid);
		session.setAttribute("logPass",loginpass);
		session.setAttribute("logName", loginname);
		session.setAttribute("logPoint", point);
		session.setAttribute("login", 1);
		}
		return "/member/loginPro.jsp";	//해당 뷰 경로 반환
	}
}
