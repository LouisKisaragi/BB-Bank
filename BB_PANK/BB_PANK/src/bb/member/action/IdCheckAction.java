package bb.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.board.action.CommandAction;
import bb.member.model.MemberDao;

public class IdCheckAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		MemberDao dbPro = MemberDao.getInstance();
		String id = request.getParameter("id");
		
		int check = dbPro.MemberIdCheck(id);

		request.setAttribute("id", id);
		request.setAttribute("check",check);
		return "/bbmember/idCheck.jsp";
		
	}
}
