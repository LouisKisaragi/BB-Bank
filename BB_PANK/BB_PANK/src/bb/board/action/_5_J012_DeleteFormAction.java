package bb.board.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class _5_J012_DeleteFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		System.out.println(pageNum);

		// 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		return "/bbboard5/m007_deleteForm.jsp";	// 해당 뷰 경로 반환
	}
}