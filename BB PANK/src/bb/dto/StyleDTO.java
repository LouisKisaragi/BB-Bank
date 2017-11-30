package jsl.dto;

public class StyleDTO {
private int num;
private String sort;
private String title;
private String image;
private String content;
private int visible;

public StyleDTO(int num, String sort, String title, String image, String content, int visible) {
	super();
	this.num = num;
	this.sort = sort;
	this.title = title;
	this.image = image;
	this.content = content;
	this.visible = visible;
}

public StyleDTO(String sort, String title, String image, String content) {
	super();
	this.sort = sort;
	this.title = title;
	this.image = image;
	this.content = content;
}



public String getSort() {
	return sort;
}

public void setSort(String sort) {
	this.sort = sort;
}

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

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public int getVisible() {
	return visible;
}

public void setVisible(int visible) {
	this.visible = visible;
}


}
