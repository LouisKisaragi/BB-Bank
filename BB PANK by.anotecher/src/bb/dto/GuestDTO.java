package bb.dto;
import java.sql.Date;

public class GuestDTO
{
	private String id;
	private String pass;
	private String name;
	private String address;
	private String phone;
	private int point;
	private Date joindate;
	private int admin;
	private int visiable;
	
	public GuestDTO() {}

	public GuestDTO(String id, String pass, String name, String address, String phone, int point, Date joindate,
			int admin, int visiable) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.point = point;
		this.joindate = joindate;
		this.admin = admin;
		this.visiable = visiable;
	}
	
	public GuestDTO(String id, String pass, String name, String address, String phone) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.address = address;
		this.phone = phone;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
