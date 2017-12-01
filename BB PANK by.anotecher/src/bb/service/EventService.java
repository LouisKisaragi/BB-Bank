package bb.service;

import java.util.ArrayList;

import jsl.dao.EventDAO;
import jsl.dto.EventDTO;

public class EventService
{
	public ArrayList<EventDTO> loadEventpage()
	{
		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		EventDAO dao = EventDAO.getinstance();
		list = dao.getAllEvent();
		
		return list;
	}
}
