package bb.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.action.CommandAction;


public class FindPassAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String page=request.getParameter("page");
		
		
	
		request.setAttribute("page",page);
		return "/bbmember/findPass.jsp";	//해당 뷰 경로 반환
	}
}
