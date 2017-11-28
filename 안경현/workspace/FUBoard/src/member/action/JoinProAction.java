package member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;
import member.model.MemberDao;
import member.model.MemberDto;


public class JoinProAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String page=request.getParameter("page");
		MemberDto article = new MemberDto();	//데이터를 처리할 빈
		System.out.println("url::"+page);
		System.out.println("id=="+request.getParameter("id"));
		System.out.println("pass=="+request.getParameter("pass"));
		System.out.println("name=="+request.getParameter("name"));
		System.out.println("email=="+request.getParameter("email"));
		System.out.println("nickname=="+request.getParameter("nickname"));
		article.setId(request.getParameter("id"));
		article.setPass(request.getParameter("pass"));
		article.setName(request.getParameter("name"));
		article.setJoindate(new Timestamp(System.currentTimeMillis()));
		article.setEmail(request.getParameter("email"));
		article.setNickname(request.getParameter("nickname"));
		MemberDao dbPro = MemberDao.getInstance(); //DB 연결
		dbPro.insertArticle(article);
		
		request.setAttribute("page",page);
		return "/member/joinPro.jsp";	//해당 뷰 경로 반환
	}
}
