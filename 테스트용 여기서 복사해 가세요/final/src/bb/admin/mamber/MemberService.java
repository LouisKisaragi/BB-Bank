package bb.admin.mamber;

import java.util.ArrayList;

import bb.admin.dao.AdminDAO;
import bb.admin.dto.MemberDTO;

public class MemberService {
	public ArrayList<MemberDTO> getAllMemberList()
	{
		AdminDAO dao = AdminDAO.getinstance();
		ArrayList<MemberDTO> list = dao.listMember();
		
		return list;
	}

}
