package bb.admin.point;
import java.util.ArrayList;

import bb.admin.dao.AdminDAO;
import bb.admin.dao.MemberDAO;
import bb.admin.dto.PointManagerDTO;

public class PointService {
	
	public ArrayList<PointManagerDTO> LoadPointLog()
	{
		ArrayList<PointManagerDTO> list = null;
		
		AdminDAO dao = AdminDAO.getinstance();
		list = dao.LoadPointLog();
		
		return list;
	}
	
	public int ModifyPoint(String id, int addPoint, String pointlog)
	{
		MemberDAO dao = MemberDAO.getinstance();
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
