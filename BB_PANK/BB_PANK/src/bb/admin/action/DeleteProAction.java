package bb.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bb.admin.model.AdBoardDao;
import bb.admin.model.AdBoardDto;
import bb.board.action.CommandAction;
import bb.board.model2.BoardDto;
import bb.member.model.MemberDao;
import bb.member.model.MemberDto;

public class DeleteProAction implements CommandAction{

	public String requestPro(
			HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		int bn=Integer.parseInt(request.getParameter("bn"));
		String pageNum = request.getParameter("pageNum");
		
		
		String location=request.getSession().getServletContext().getRealPath("/upload/");
		AdBoardDao dbPro = AdBoardDao.getInstance();
		AdBoardDto article=dbPro.getArticle(num);
		int mem=article.getMem();
		int check = dbPro.deleteMArticle(num, location);
		
		//System.out.println("check"+check);
		//System.out.println("num"+num);
		//System.out.println("pass"+pass);
		
		if(check==1) {
			if(mem==1) {
				MemberDao dbMPro= MemberDao.getInstance();//회원 DB연결
				String id=article.getWriter();
				MemberDto articleM = dbMPro.memberSeeArticle(id);
				dbMPro.MemberPoint(articleM.getNickname(),-10);
				}
		}
		//뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		request.setAttribute("bn", new Integer(bn));
		return "/bbadmin/addeletePro.jsp"; //뷰 경로
	}
}
