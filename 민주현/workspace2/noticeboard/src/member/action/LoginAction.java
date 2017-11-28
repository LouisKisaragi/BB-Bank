package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;

public class LoginAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		
		
		String page=null;
		
		if(request.getParameter("url")==null) {
			page =request.getHeader("referer");
		}else {
			page=request.getParameter("url");
			System.out.println("takeurl:"+request.getParameter("url"));
		}
		System.out.println("url:=:"+page);

	
		request.setAttribute("page", page);
		return "/member/login.jsp";
	}
}
