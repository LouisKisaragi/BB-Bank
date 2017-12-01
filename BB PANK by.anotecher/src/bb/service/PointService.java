package bb.service;

import java.util.*;

import jsl.dao.AdminDAO;
import jsl.dao.GuestDAO;
import jsl.dto.PointManagerDTO;

public class PointService
{
	public ArrayList<PointManagerDTO> LoadPointLog()
	{
		ArrayList<PointManagerDTO> list = null;
		
		AdminDAO dao = AdminDAO.getinstance();
		list = dao.LoadPointLog();
		
		return list;
	}
	
	public int ModifyPoint(String id, int addPoint, String pointlog)
	{
		GuestDAO dao = GuestDAO.getinstance();
		AdminDAO admin_dao = AdminDAO.getinstance();
		int userPoint = dao.getUserPoint(id);
		int newPoint;
		if(userPoint == -1)
		{
			return -1;
		}
		else 
		{
			newPoint = userPoint + addPoint;
			dao.modifyPoint(id, newPoint);
			admin_dao.insertPointlog(id, addPoint, pointlog);
		}
		return 0;
	}

}
