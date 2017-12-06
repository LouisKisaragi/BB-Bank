package bb.board;
import java.util.ArrayList;
import bb.dao.BoardDAO;
import bb.dto.BoardDTO;

public class BoardService {
	// 게시판 관련 DAO 서비스를 제공하는 클래스.
	public ArrayList<BoardDTO> getBoardList(String message)	{
		BoardDAO dao = BoardDAO.getinstance();
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		list = dao.getAllcontent(message);
		
		return list;
	}
	
	/*public ArrayList<QnADTO> getQnAList()	{
		BoardDAO dao = BoardDAO.getinstance();
		ArrayList<QnADTO> list = new ArrayList<QnADTO>();
		
		list = dao.getAllQnA();
		
		return list;
	}
	*/
	public BoardDTO getOneNote(int num)	{
		BoardDAO dao = BoardDAO.getinstance();
		BoardDTO dto = null;
		
		dto = dao.getOneNote(num);
		
		return dto;
	}
}