package member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;
import board.model.BoardDao;
import member.model.MemberDao;
import member.model.MemberDto;


public class LoginProAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String page=request.getParameter("page");
		MemberDto article = new MemberDto();	//데이터를 처리할 빈
		System.out.println("url::"+page);
		System.out.println("id=="+request.getParameter("id"));
		System.out.println("pass=="+request.getParameter("pass"));
		article.setId(request.getParameter("id"));
		article.setPass(request.getParameter("pass"));
		MemberDao dbPro = MemberDao.getInstance(); //DB 연결
		int check = dbPro.loginArticle(id,pass);
		request.setAttribute("check", check);
		request.setAttribute("page",page);
		return "/member/joinPro.jsp";	//해당 뷰 경로 반환
	}
}
