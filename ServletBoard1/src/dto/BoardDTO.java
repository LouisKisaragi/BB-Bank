package dto;

public class BoardDTO {
	private int num;
	private String name;
	private String title;
	private String contents;
	private String image;
	
	public BoardDTO() {}

	public BoardDTO(int num, String name, String title, String contents, String image) {
		super();
		this.num = num;
		this.name = name;
		this.title = title;
		this.contents = contents;
		this.image = image;
	}	

	public BoardDTO(String name, String title, String contents, String image) {
		super();
		this.name = name;
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
	
	public String getName() {
		return name;
	}

	public void setId(String name) {
		this.name = name;
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
}