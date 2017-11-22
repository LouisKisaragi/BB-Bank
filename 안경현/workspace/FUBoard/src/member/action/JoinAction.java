package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.CommandAction;

public class JoinAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		
		
		
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		int bn =Integer.parseInt(request.getParameter("bn"));
		int num = Integer.parseInt(request.getParameter("num"));
		if(request.getParameter("pageNum")==null) {
			pageNum=0;
		}
		if(request.getParameter("bn")==null) {
			bn=0;
		}
		if(request.getParameter("num")==null) {
			num=0;
		}
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("bn", bn);
		request.setAttribute("num",num);
		return "/member/join.jsp";
	}
}
