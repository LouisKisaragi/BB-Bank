package jsl.mypage;

import java.util.ArrayList;

import jsl.dao.MypageDAO;
import jsl.dto.PointManagerDTO;
import jsl.dto.QnADTO;
import jsl.dto.ReservationDTO;

public class MypageService {
		
	private String id;
	public MypageService(){}
	public MypageService(String id){
		this.id = id;
	}
	//GuestDTO dto = (GuestDTO)session.getAttrivute("login");
	//dto.getid();  jsp쪽에 필요..

	public ArrayList<QnADTO> getBoardList() {
		MypageDAO dao = MypageDAO.getinstance();
		ArrayList<QnADTO> list1 = new ArrayList<QnADTO>();

		list1 = dao.getqnaAllcontent(id);

		return list1;
	}
		
	public ArrayList<PointManagerDTO> getPointList() {
		MypageDAO dao = MypageDAO.getinstance();
		ArrayList<PointManagerDTO> list1 = new ArrayList<PointManagerDTO>();

		list1 = dao.getpointAllcontent(id);
		return list1;
	}
	
	public ArrayList<ReservationDTO> getReservationList() {
		MypageDAO dao = MypageDAO.getinstance();
		ArrayList<ReservationDTO> list1 = new ArrayList<ReservationDTO>();

		list1 = dao.getreservationAllcontent(id);

		return list1;
	}
	
	public int getMyPoint()
	{
		MypageDAO dao = MypageDAO.getinstance();
		return dao.getUserPoint(id);
	}
	
	
	
}
