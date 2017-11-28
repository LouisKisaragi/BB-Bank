package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.action.CommandAction;

public class LogoutAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String page=request.getHeader("referer");
		System.out.println("url:=:"+page);
		session.setAttribute("login", 0);
		session.invalidate();
		request.setAttribute("page", page);
		return "/member/logoutPro.jsp";
	}
}
