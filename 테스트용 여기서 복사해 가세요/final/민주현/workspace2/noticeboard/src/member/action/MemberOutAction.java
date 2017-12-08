package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;

public class MemberOutAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		return "/member/memberOut.jsp";
	}
}
