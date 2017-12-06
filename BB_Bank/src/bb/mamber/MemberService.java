package bb.mamber;

import java.util.ArrayList;

import bb.dao.AdminDAO;
import bb.dto.MemberDTO;

public class MemberService {
	public ArrayList<MemberDTO> getAllMemberList()
	{
		AdminDAO dao = AdminDAO.getinstance();
		ArrayList<MemberDTO> list = dao.listMember();
		
		return list;
	}

}
