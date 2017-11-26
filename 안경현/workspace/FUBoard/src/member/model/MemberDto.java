package member.model;

import java.sql.Timestamp;

public class MemberDto {
	private int pnum;
	private String name;
	private String id;
	private String pass;
	private String email;
	private int point;
	private String super_m;
	private Timestamp joindate;
	
	
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	public Timestamp getJoindate() {
		return joindate;
	}
	public void setJoindate(Timestamp joindate) {
		this.joindate = joindate;
	}
	public String getSuper_m() {
		return super_m;
	}
	public void setSuper_m(String super_m) {
		this.super_m = super_m;
	}

	
	
	
	
}
