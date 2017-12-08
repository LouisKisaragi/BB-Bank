package bb.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.board.action.CommandAction;
import bb.member.model.MemberDao;

public class MemberOutProAction  implements CommandAction{
	public String requestPro (
			HttpServletRequest request,
			HttpServletResponse response)throws Throwable{
		request.setCharacterEncoding("UTF-8");
		//세션생성
		HttpSession session = request.getSession(true);//세션이 있는지 없는지를 확인 
		//있으면 세션을 반환시키고. 없으면 생성

				
				String id = (String) session.getAttribute("logId");
				String pass=request.getParameter("pass");
				if(id==null) {
					int check=-2;
					request.setAttribute("check",check);
					return "/member/memberOutPro.jsp";
				}
				System.out.println("id:"+id+",pass:"+pass);
				MemberDao dbPro = MemberDao.getInstance(); //DB 연결
				int check = dbPro.memberOut(id,pass);
				if(check==1) {
					session.setAttribute("login", 0);
					session.invalidate();
				}
				request.setAttribute("check", check);
			
				
	
		
		
		return "/bbmember/memberOutPro.jsp";
	}

}
