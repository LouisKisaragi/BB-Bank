package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.action.CommandAction;

public class EmailCertificationAction implements CommandAction{
	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession(true);//세션이 있는지 없는지를 확인 
		//있으면 세션을 반환시키고. 없으면 생성
		String authNum=(String)session.getAttribute("authNum");
	
		String page=request.getParameter("page");

		System.out.println(authNum);
		System.out.println(request.getParameter("certification"));
		if(authNum.equals(request.getParameter("certification"))) {
			System.out.println("123");
			request.setAttribute("check", 1);
		}else {
			System.out.println("456");
			request.setAttribute("check", -1);
		}
		request.setAttribute("page", page);
		return "/member/emailCheckPro.jsp";
	}
}