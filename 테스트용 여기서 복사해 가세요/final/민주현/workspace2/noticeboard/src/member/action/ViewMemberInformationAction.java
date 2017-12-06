package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;
import member.model.MemberDao;
import member.model.MemberDto;

public class ViewMemberInformationAction  implements CommandAction{
	public String requestPro (
			HttpServletRequest request,
			HttpServletResponse response)throws Throwable{
		request.setCharacterEncoding("UTF-8");
		
		MemberDao dbPro = MemberDao.getInstance();
		
		String viewNick= request.getParameter("viewNick");
		System.out.println(viewNick);
		MemberDto article = dbPro.memberSeeArticle(viewNick);
		
		request.setAttribute("viewNick", viewNick);
		request.setAttribute("article",article);
		return "/member/viewMemberInformation.jsp";
	}

}
