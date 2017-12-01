package bb.dto;

import java.sql.Date;

public class PointManagerDTO
{ // 포인트 내역 관리를 위한 DTO
	private String guest_id;
	private int p_calcul;
	private String p_cont;
	private Date p_date;
	
	public PointManagerDTO () {}

	public PointManagerDTO(String guest_id, int p_calcul, String p_cont, Date p_date) {
		super();
		this.guest_id = guest_id;
		this.p_calcul = p_calcul;
		this.p_cont = p_cont;
		this.p_date = p_date;
	}

	public String getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(String guest_id) {
		this.guest_id = guest_id;
	}

	public int getP_calcul() {
		return p_calcul;
	}

	public void setP_calcul(int p_calcul) {
		this.p_calcul = p_calcul;
	}

	public String getP_cont() {
		return p_cont;
	}

	public void setP_cont(String p_cont) {
		this.p_cont = p_cont;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}
}
