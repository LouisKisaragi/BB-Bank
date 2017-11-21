package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction{
	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		//제목글과 답변글의 구분
		int num=0, ref = 1, step=0, depth=0;
		try{
			if(request.getParameter("num") != null){
				num = Integer.parseInt(request.getParameter("num"));
				ref = Integer.parseInt(request.getParameter("ref"));
				step = Integer.parseInt(request.getParameter("step"));
				depth = Integer.parseInt(request.getParameter("depth"));
				
			}
		} catch (Exception e) { e.printStackTrace(); }
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		int bn = Integer.parseInt(request.getParameter("bn"));
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("bn", bn);
		//해당 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("step", new Integer(step));
		request.setAttribute("depth", new Integer(depth));
		return "/board/writeForm.jsp";	//해당 뷰 경로 반환
	}
}
