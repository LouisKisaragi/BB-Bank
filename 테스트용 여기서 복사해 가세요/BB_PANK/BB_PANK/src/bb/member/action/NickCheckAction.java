package bb.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.action.CommandAction;
import bb.member.model.MemberDao;

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
		return "/bbmember/nickCheck.jsp";
		
	}
}
