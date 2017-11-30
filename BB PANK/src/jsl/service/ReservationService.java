package jsl.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jsl.dao.BookingDAO;
import jsl.dao.MypageDAO;
import jsl.dto.ReservationDTO;

public class ReservationService
{
	public ArrayList<ReservationDTO> getAllReservation()
	{
		ArrayList<ReservationDTO> list = null;
		
		BookingDAO dao = BookingDAO.getinstance();
		list = dao.getAllcontent();
		return list;
	}
	
	public ArrayList<ReservationDTO> getOneReservation(String id)
	{
		ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
		MypageDAO dao = MypageDAO.getinstance();
		list = dao.getreservationAllcontent(id);
		
		return list;
	}
	
	public void writeReservation(ReservationDTO reservationDTO)
	{
		BookingDAO dao = BookingDAO.getinstance();
		dao.writeReservation(reservationDTO);
	}
	
	public boolean checkReservation(Date res_day1, String res_time, int designer_id) {
		ArrayList<ReservationDTO> list = getAllReservation();
		boolean resultvalue = true;		
		SimpleDateFormat formating = new SimpleDateFormat("yy-MM-dd");
		String res_day = formating.format(res_day1);
		String get_day = null;
		
		for (int i=0; i<list.size(); i++) {
			ReservationDTO dto = list.get(i);
			get_day = formating.format(dto.getRes_day());
			if(get_day.equals(res_day) && res_time.equals(dto.getRes_time())) {
				if(designer_id == dto.getDesigner_id()) {
					resultvalue = false;
				}
			}
		}
		System.out.println(resultvalue);
		return resultvalue;
	}
	
	public String getOneDesigner(int des_no)
	{
		String returnValue = null;
		BookingDAO dao = BookingDAO.getinstance();
		returnValue = dao.getOneDesignerName(des_no);
		
		return returnValue;	
	}
	
	public String getContents(int num)
	{
		if(num ==1) return "カット";
		else if(num == 2) return "パーマ";
		else return "カラー";
	}
	
	public String genderValue(int num)
	{
		if(num == 1) return "MAN";
		else return "WOMAN";
	}
}
