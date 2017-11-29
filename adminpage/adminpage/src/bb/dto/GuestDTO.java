package bb.dto;

import java.sql.Date;

public class GuestDTO {
	private String id;
	private String name;
	private String pass;
	private String email;
	private int admin;
	private int visiable;
	private int point;
	private Date joindate;
	
	public GuestDTO(){}
	//
	public GuestDTO(String nickname, String pass, String email, int admin, Date joindate, int point, int visiable) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.admin = admin;
		this.visiable = visiable;
		this.point = point;
		this.joindate = joindate;
		
	}
	
	public GuestDTO(String id, String pass, String name, String address, String phone) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getVisiable() {
		return visiable;
	}
	public void setVisiable(int visiable) {
		this.visiable = visiable;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

}
