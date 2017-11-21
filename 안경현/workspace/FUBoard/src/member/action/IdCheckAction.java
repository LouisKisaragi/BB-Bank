package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;

public class IdCheckAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
	
		String id = request.getParameter("id");
		
		request.setAttribute("id", id);
		return "/member/idCheck.jsp";
		
	}
}
