package bb.dto;

public class QnADTO
{
	private int number;
	private String guest_id;
	private String title;
	private String contents;
	private String answer;
	
	public QnADTO() {
		super();
	}

	public QnADTO(int number, String guest_id, String title, String contents, String answer) {
		super();
		this.number = number;
		this.guest_id = guest_id;
		this.title = title;
		this.contents = contents;
		this.answer = answer;
	}

	public QnADTO(String guest_id, String title, String contents) {
		super();
		this.guest_id = guest_id;
		this.title = title;
		this.contents = contents;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(String guest_id) {
		this.guest_id = guest_id;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
