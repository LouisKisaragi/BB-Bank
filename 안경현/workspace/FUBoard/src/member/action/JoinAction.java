package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;

public class JoinAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		
		
		String page=request.getHeader("referer");
		System.out.println("url::"+page);

	
		request.setAttribute("page", page);
		return "/member/login.jsp";
	}
}
