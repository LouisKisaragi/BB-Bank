package bb.board.action2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction  implements CommandAction{
	public String requestPro (
			HttpServletRequest request,
			HttpServletResponse response)throws Throwable{
		request.setCharacterEncoding("UTF-8");
		
		return "/bbboard2/Main.jsp";
	}

}
