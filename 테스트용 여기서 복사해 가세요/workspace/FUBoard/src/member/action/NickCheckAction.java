package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;
import member.model.MemberDao;

public class NickCheckAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		MemberDao dbPro = MemberDao.getInstance();
		String nick = request.getParameter("nick");
		int check = dbPro.MemberNickCheck(nick);

		request.setAttribute("nick", nick);
		request.setAttribute("check",check);
		return "/member/nickCheck.jsp";
		
	}
}
