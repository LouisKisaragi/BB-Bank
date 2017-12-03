package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;

public class ViewMemberInformationAction  implements CommandAction{
	public String requestPro (
			HttpServletRequest request,
			HttpServletResponse response)throws Throwable{
		request.setCharacterEncoding("UTF-8");
		
		String viewNick= request.getParameter("viewNick");
		request.setAttribute("viewNick", viewNick);
		return "/member/viewMemberInformation.jsp";
	}

}
