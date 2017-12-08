package bb.admin.action;

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
		

				
				String id = request.getParameter("id");
				String pass=request.getParameter("pass");
				System.out.println("id:"+id+",pass:"+pass);
				MemberDao dbPro = MemberDao.getInstance(); //DB 연결
				int check = dbPro.memberOut(id,pass);
				
				request.setAttribute("check", check);
			
				
	
		
		
		return "/bbadmin/memberOutPro.jsp";
	}

}
