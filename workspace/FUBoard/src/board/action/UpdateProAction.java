package board.action;

import static java.lang.Math.toIntExact;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import board.model.BoardDao;
import board.model.BoardDto;

public class UpdateProAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum");
		String contentType=null;
		String origin_filename=null;
		String server_filename=null;
		int filesize=0;
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDao dbProc = BoardDao.getInstance();
		
		//해당 글번호에 대한 레코드
		BoardDto articlec = dbProc.getArticle(num);
		String DB_S_filename = articlec.getServer_filename();
		String DB_O_filename = articlec.getOrigin_filename();
		String location=request.getSession().getServletContext().getRealPath("/upload/");
//		//System.out.println(filename);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Collection<Part> parts=request.getParts();
		for(Part part : parts) {
			contentType= part.getContentType();
			//System.out.println("CT:" + contentType);
			if(contentType==null) {
				part.delete();
			}else if( contentType.startsWith("application/")) {
				long size=part.getSize();
				////System.out.println("size ::"+size);
				filesize=toIntExact(size);
				////System.out.println("filesize::"+filesize);
				origin_filename = getFileName(part);
				server_filename = request.getParameter("num")+"_"+size+"_"+(contentType.substring((contentType.indexOf("/"))+1))+"_"+origin_filename;
			//	setString(filename,contentType,server_filename);
				////System.out.println("fn::"+origin_filename);
				////System.out.println("server_filename= ::"+server_filename);
				if(size > 0) {
					part.write(location + server_filename);
					part.delete();
				}
			}else if( contentType.startsWith("image/")) {
				long size=part.getSize();
				////System.out.println("size ::"+size);
				filesize=toIntExact(size);
				//System.out.println("filesize::"+filesize);
				origin_filename = getFileName(part);
				server_filename = request.getParameter("num")+"_"+size+"_"+(contentType.substring(contentType.indexOf("/")+1))+"_"+origin_filename;
			//	setString(origin_filename,contentType,server_filename);
				//System.out.println("fn::"+origin_filename);
				//System.out.println("server_filename= ::"+server_filename);
				if(size > 0) {
					part.write(location + server_filename);
					part.delete();
				}
			}else if( contentType.startsWith("text/")) {
				long size=part.getSize();
				filesize=toIntExact(size);
				//System.out.println("size ::"+size);
				//System.out.println("filesize::"+filesize);
				origin_filename = getFileName(part);
				server_filename = request.getParameter("num")+"_"+size+"_"+(contentType.substring(contentType.indexOf("/")+1))+"_"+origin_filename;
			//	setString(origin_filename,contentType,server_filename);
				//System.out.println("server_filename= ::"+server_filename);
				if(size > 0) {
					part.write(location + server_filename);
					part.delete();
				}
			}
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		request.setCharacterEncoding("UTF-8");
		BoardDto article = new BoardDto();	//데이터를 처리할 빈
		if(origin_filename.equals("")&&filesize==0) {
			//파일을 삭제할경우
			//System.out.println("파일삭제");
			//System.out.println("삭제전:"+server_filename);
			File f = new File(location+server_filename);
			f.delete();
			server_filename=null;
			origin_filename=null;
			contentType=null;
			//System.out.println("13");
			//System.out.println("삭제후:"+server_filename);
		}else if(origin_filename.equals(DB_O_filename)){
			//파일이 그대로일 경우
			//System.out.println("1");
		}else {
			//파일을 변경할 경우
			//System.out.println("파일수정");
			//System.out.println("삭제할 파일 명:"+filename);
			File f = new File(location+DB_S_filename);
			f.delete();
			//System.out.println("21");
		}
		//System.out.println("filesize::"+filesize);
		//int s = filesize+10;
		//System.out.println("확인 filesize+10 ::"+s);
		//System.out.println("ct="+contentType);
		//System.out.println("fn="+origin_filename);
		//System.out.println("sfn="+server_filename);
		//System.out.println("writer=="+request.getParameter("writer"));
		//System.out.println("subject=="+request.getParameter("subject"));
		//System.out.println("pass=="+request.getParameter("pass"));
		//System.out.println("content=="+request.getParameter("content"));
		//System.out.println("preface=="+request.getParameter("preface"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setOrigin_filename(origin_filename);
		article.setSubject(request.getParameter("subject"));
		article.setPass(request.getParameter("pass"));
		article.setContent(request.getParameter("content"));
		article.setFiletype(contentType);
		article.setServer_filename(server_filename);
		article.setFilesize(filesize);
		article.setPreface(request.getParameter("preface"));
		BoardDao dbPro = BoardDao.getInstance(); //DB 연결
		int check = dbPro.updateArticle(article);
		//뷰에 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
			//System.out.println("chek"+check);
		return "/board/updatePro.jsp";	//해당 뷰 경로 반환
	}
	private String getFileName(Part part) throws UnsupportedEncodingException{
		for( String cd : part.getHeader("Content-Disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				String tmp = cd.substring(cd.indexOf('=')+
						1).trim().replace("\"", "");
				tmp=tmp.substring(tmp.indexOf(":")+1);
				return tmp;
			}
			
		}return null;
	}
}
