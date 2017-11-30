package jsl.service;

import java.util.ArrayList;

import jsl.dao.StyleDAO;
import jsl.dto.StyleDTO;

public class StyleService {

	public ArrayList<StyleDTO> getStyleList()
	{
		StyleDAO dao = StyleDAO.getinstance();
		ArrayList<StyleDTO> list = new ArrayList<StyleDTO>();
		
		list = dao.getAllcontent();
		
		return list;
	}
}
