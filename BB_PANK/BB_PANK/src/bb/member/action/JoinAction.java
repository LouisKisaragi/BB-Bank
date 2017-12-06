package bb.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.action.CommandAction;

public class JoinAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String page=request.getHeader("referer");
		System.out.println("url::"+page);

	
		request.setAttribute("page", page);
		return "/bbmember/join.jsp";
	}
}
