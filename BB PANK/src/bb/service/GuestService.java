package bb.service;

import java.util.ArrayList;

import jsl.dao.AdminDAO;
import jsl.dto.GuestDTO;

public class GuestService
{
	public ArrayList<GuestDTO> getAllGuestList()
	{
		AdminDAO dao = AdminDAO.getinstance();
		ArrayList<GuestDTO> list = dao.listMember();
		
		return list;
	}
}
