package jsl.dto;

public class EventDTO
{
	private int num;
	private String title;
	private String content;
	private String image;
	private String linkvalue;
	private String datevalue;
	private int visiable;
	
	public EventDTO(int num, String title, String content, String image, String linkvalue, String datevalue,
			int visiable) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.image = image;
		this.linkvalue = linkvalue;
		this.datevalue = datevalue;
		this.visiable = visiable;
	}
	
	public EventDTO(String title, String content, String image, String linkvalue, String datevalue) {
		super();
		this.title = title;
		this.content = content;
		this.image = image;
		this.linkvalue = linkvalue;
		this.datevalue = datevalue;
	}



	public EventDTO() {}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLinkvalue() {
		return linkvalue;
	}

	public void setLinkvalue(String linkvalue) {
		this.linkvalue = linkvalue;
	}

	public String getDatevalue() {
		return datevalue;
	}

	public void setDatevalue(String datevalue) {
		this.datevalue = datevalue;
	}

	public int getVisiable() {
		return visiable;
	}

	public void setVisiable(int visiable) {
		this.visiable = visiable;
	}
	
	
}
