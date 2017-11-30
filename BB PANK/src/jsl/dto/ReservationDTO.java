package jsl.dto;

import java.util.Date;

public class ReservationDTO {
	private int num;
	private String guest_id;
	private int gender;
	private int contents;
	private Date res_day;
	private String res_time;
	private int designer_id;
	private int visiable;
	
	public ReservationDTO(){}
	
	public ReservationDTO(int num, String guest_id, int gender, int contents, Date res_day, String res_time,
			int designer_id, int visiable) {
		this.num = num;
		this.guest_id = guest_id;
		this.gender = gender;
		this.contents = contents;
		this.res_day = res_day;
		this.res_time = res_time;
		this.designer_id = designer_id;
		this.visiable = visiable;		
	}
	
	public ReservationDTO(String guest_id, int gender, int contents, Date res_day, String res_time,
			int designer_id, int num) {
		
		this.guest_id = guest_id;
		this.gender = gender;
		this.contents = contents;
		this.res_day = res_day;
		this.res_time = res_time;
		this.designer_id = designer_id;
		this.num = num;
	}
	public ReservationDTO(String guest_id, int gender, int contents, Date res_day, String res_time, int designer_id) {
		this.guest_id = guest_id;
		this.gender = gender;
		this.contents = contents;
		this.res_day = res_day;
		this.res_time = res_time;
		this.designer_id = designer_id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(String guest_id) {
		this.guest_id = guest_id;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getContents() {
		return contents;
	}

	public void setContents(int contents) {
		this.contents = contents;
	}

	public Date getRes_day() {
		return res_day;
	}

	public void setRes_day(Date res_day) {
		this.res_day = res_day;
	}

	public String getRes_time() {
		return res_time;
	}

	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}

	public int getDesigner_id() {
		return designer_id;
	}

	public void setDesigner_id(int designer_id) {
		this.designer_id = designer_id;
	}

	public int getVisiable() {
		return visiable;
	}

	public void setVisiable(int visiable) {
		this.visiable = visiable;
	}
		
	
}
