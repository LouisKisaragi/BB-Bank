package bb.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.board.action2.CommandAction;
import bb.member.model.MemberDao;

public class FindPassProAction implements CommandAction{
	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);//세션이 있는지 없는지를 확인 
		//있으면 세션을 반환시키고. 없으면 생성
		String authNum=(String)session.getAttribute("authNum");
		String email= request.getParameter("email");
		String id=request.getParameter("id");

		MemberDao dbPro = MemberDao.getInstance();
		
		String pass = dbPro.getPass(id,email);
		System.out.println(authNum);
		System.out.println(request.getParameter("certification"));
		if(authNum.equals(request.getParameter("certification"))) {
			request.setAttribute("check", 1);
			request.setAttribute("pass", pass);
			System.out.println("pass"+pass);
		}else {
			request.setAttribute("check", -1);
		}
		request.setAttribute("page", session.getAttribute("returnPage"));
		session.setAttribute("returnPage", 0);
		session.setAttribute("authNum",0);
		session.invalidate();
		
		return "/bbmember/findPassPro.jsp";
	}
}