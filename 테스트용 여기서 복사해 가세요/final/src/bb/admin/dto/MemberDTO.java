package bb.admin.dto;
import java.sql.*;

public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String email;
	private int point;
	private Date joindate;
	private int admin;
	private int visiable;
	
	public MemberDTO() {}

	public MemberDTO(String id, String pass, String name, String email, int point, Date joindate,
			int admin, int visiable) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.point = point;
		this.joindate = joindate;
		this.admin = admin;
		this.visiable = visiable;
	}
	
	public MemberDTO(String id, String pass, String name, String email) {
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
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
}