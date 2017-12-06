package bb.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.board.action.CommandAction;
import bb.member.model.MemberDao;

public class FindIdProAction implements CommandAction{
	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);//세션이 있는지 없는지를 확인 
		//있으면 세션을 반환시키고. 없으면 생성
		String authNum=(String)session.getAttribute("authNum");
		String name= request.getParameter("name");
		String email=request.getParameter("email");
		MemberDao dbPro = MemberDao.getInstance();
		
		String id = dbPro.getId(name,email);
		System.out.println(authNum);
		System.out.println(request.getParameter("certification"));
		if(authNum.equals(request.getParameter("certification"))) {
			request.setAttribute("check", 1);
			System.out.println("id:"+id);
			request.setAttribute("id", id);
		}else {
			request.setAttribute("check", -1);
		}
		System.out.println("page:"+session.getAttribute("returnPage"));
		request.setAttribute("page", session.getAttribute("returnPage"));
		
		session.setAttribute("returnPage", 0);
		session.setAttribute("authNum",0);
		session.invalidate();
		
		return "/bbmember/findIdPro.jsp";
	}
}