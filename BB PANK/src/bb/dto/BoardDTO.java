package jsl.dto;

public class BoardDTO {
	private int num;
	private String id;
	private String title;
	private String contents;
	private String image;
	private int visiable;
	
	public BoardDTO() {}

	public BoardDTO(int num, String id, String title, String contents, String image, int visiable) {
		super();
		this.num = num;
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.image = image;
		this.visiable = visiable;
	}	

	public BoardDTO(String id, String title, String contents, String image) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.image = image;
	}

	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

	public int getVisiable() {
		return visiable;
	}

	public void setVisiable(int visiable) {
		this.visiable = visiable;
	}
	
	
	
}
