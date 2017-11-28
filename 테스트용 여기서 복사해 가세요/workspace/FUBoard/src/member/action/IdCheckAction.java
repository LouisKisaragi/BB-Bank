package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;
import member.model.MemberDao;

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
		return "/member/idCheck.jsp";
		
	}
}
