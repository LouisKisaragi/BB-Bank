package bb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bb.board.model2.BoardDao;
import bb.board.model2.BoardDto;
import bb.member.model.MemberDao;
import bb.member.model.MemberDto;

public class _2DeleteProAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		int bn=Integer.parseInt(request.getParameter("bn"));
		String pageNum = request.getParameter("pageNum");
		HttpSession session = request.getSession(false);
		String pass = null;
		//System.out.println("pass"+session.getAttribute("logPass"));
		if(session.getAttribute("logPass")==null) {
			pass = request.getParameter("pass");
		}else {
			pass=(String)session.getAttribute("logPass");
		}
		String location=request.getSession().getServletContext().getRealPath("/upload/");
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article=dbPro.getArticle(num);
		int mem=article.getMem();
		int check = dbPro.deleteArticle(num, pass, location);
		
		//System.out.println("check"+check);
		//System.out.println("num"+num);
		//System.out.println("pass"+pass);
		
		if(check==1) {
			if(mem==1) {
				MemberDao dbMPro= MemberDao.getInstance();//회원 DB연결
				dbMPro.MemberPoint(session.getAttribute("logNick"),-10);
				String id=article.getWriter();
				MemberDto articleM = dbMPro.memberSeeArticle(id);
				session.setAttribute("logPoint", articleM.getPoint());
				}
		}
		//뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		request.setAttribute("bn", new Integer(bn));
		return "/bbboard2/deletePro.jsp"; //뷰 경로
	}
}
