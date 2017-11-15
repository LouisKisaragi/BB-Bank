package board.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import board.model.BoardDao;
import board.model.BoardDto;
import static java.lang.Math.toIntExact;

public class WriteProAction implements CommandAction{


	public String requestPro(
			HttpServletRequest request, 
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		String contentType=null;
		String origin_filename=null;
		String server_filename=null;
		String location=request.getSession().getServletContext().getRealPath("/upload/");
		int filesize=0;
		///////////////////////////////////////////////////////////////////////////////////////////////////
		Collection<Part> parts=request.getParts();
		for(Part part : parts) {
			contentType= part.getContentType();
			System.out.println("CT:" + contentType);
			if(contentType==null) {
				part.delete();
			}else if( contentType.startsWith("application/")) {
				long size=part.getSize();
				System.out.println("size ::"+size);
				filesize=toIntExact(size);
				System.out.println("filesize::"+filesize);
				origin_filename = getFileName(part);
				server_filename = request.getParameter("num")+"_"+size+"_"+(contentType.substring((contentType.indexOf("/"))+1))+"_"+origin_filename;
			//	setString(filename,contentType,server_filename);
				System.out.println("fn::"+origin_filename);
				System.out.println("server_filename= ::"+server_filename);
				if(size > 0) {
					part.write(location+ server_filename);
					part.delete();
				}
			}else if( contentType.startsWith("image/")) {
				long size=part.getSize();
				System.out.println("size ::"+size);
				filesize=toIntExact(size);
				System.out.println("filesize::"+filesize);
				origin_filename = getFileName(part);
				server_filename = request.getParameter("num")+"_"+size+"_"+(contentType.substring(contentType.indexOf("/")+1))+"_"+origin_filename;
			//	setString(origin_filename,contentType,server_filename);
				System.out.println("fn::"+origin_filename);
				System.out.println("server_filename= ::"+server_filename);
				if(size > 0) {
					part.write(location + server_filename);
					part.delete();
				}
			}else if( contentType.startsWith("text/")) {
				long size=part.getSize();
				filesize=toIntExact(size);
				System.out.println("size ::"+size);
				System.out.println("filesize::"+filesize);
				origin_filename = getFileName(part);
				server_filename = request.getParameter("num")+"_"+size+"_"+(contentType.substring(contentType.indexOf("/")+1))+"_"+origin_filename;
			//	setString(origin_filename,contentType,server_filename);
				System.out.println("server_filename= ::"+server_filename);
				if(size > 0) {
					part.write(location + server_filename);
					part.delete();
				}
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////
		request.setCharacterEncoding("UTF-8");
		BoardDto article = new BoardDto();	//데이터를 처리할 빈
	//	saveFile(request);
		System.out.println("filesize::+"+filesize);
		System.out.println("ct="+contentType);
		System.out.println("fn="+origin_filename);
		System.out.println("sfn="+server_filename);
		System.out.println("writer=="+request.getParameter("writer"));
		System.out.println("subject=="+request.getParameter("subject"));
		System.out.println("pass=="+request.getParameter("pass"));
		System.out.println("ref=="+request.getParameter("ref"));
		System.out.println("step=="+request.getParameter("step"));
		System.out.println("depth=="+request.getParameter("depth"));
		System.out.println("content=="+request.getParameter("content"));
		System.out.println("bn=="+request.getParameter("bn"));
		System.out.println("preface=="+request.getParameter("preface"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setOrigin_filename(origin_filename);
		article.setSubject(request.getParameter("subject"));
		article.setPass(request.getParameter("pass"));
		article.setRegdate(new Timestamp(System.currentTimeMillis()));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setStep(Integer.parseInt(request.getParameter("step")));
		article.setDepth(Integer.parseInt(request.getParameter("depth")));
		article.setContent(request.getParameter("content"));
		article.setFiletype(contentType);
		article.setServer_filename(server_filename);
		article.setFilesize(filesize);
		article.setBn(Integer.parseInt(request.getParameter("bn")));
		article.setPreface(request.getParameter("preface"));
		article.setIp(request.getRemoteAddr());
		BoardDao dbPro = BoardDao.getInstance(); //DB 연결
		dbPro.insertArticle(article);
		return "/board/writePro.jsp";	//해당 뷰 경로 반환
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
