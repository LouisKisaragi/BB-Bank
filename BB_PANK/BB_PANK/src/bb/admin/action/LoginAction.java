package bb.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.board.action.CommandAction;

public class LoginAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);//세션이 있는지 없는지를 확인 
		
		String page =request.getHeader("referer");
	
		//System.out.println("url:=:"+page);
		
		session.setAttribute("returnPage", page);
	
		request.setAttribute("page", page);
		return "/bbadmin/adlogin.jsp";
	}
}
