package bb.board.model5;
import java.sql.Timestamp;
/*	테이블 생성하는 쿼리문 - 이 DB를 토대로 만들어진 DTO다.
CREATE table "BOARD"(
	"NUM" NUMBER(7,0) NOT NULL,
	"WRITER" VARCHAR2(12) NOT NULL,
	"SUBJECT" VARCHAR2(500) NOT NULL,
	"PASS" VARCHAR2(10) NOT NULL,
	"READCOUNT" NUMBER(5,0) DEFAULT 0 NOT NULL,
	"REF" NUMBER(3,0) DEFAULT 0 NOT NULL,
	"STEP" NUMBER(3,0) DEFAULT 0 NOT NULL,
	"DEPTH" NUMBER(3,0) DEFAULT 0 NOT NULL,
	"REGDATE" TIMESTAMP DEFAULT SYSDATE NOT NULL,
	"CONTENT" VARCHAR2(4000) NOT NULL,
	"IP" VARCHAR2(20) NOT NULL,
	"BN" NUMBER(2,0) NOT NULL,
	"ORIGIN_FILENAME" VARCHAR2(256),
	"SERVER_FILENAME" VARCHAR2(256),
	"FILESIZE" NUMBER(7,0),
	"FILETYPE" VARCHAR2(256),
	"PREFACE" VARCHAR2(50) NOT NULL,
	"MEM" NUMBER(1,0) DEFAULT 0 NOT NULL,
	constraint "BOARD_PK" primary key ("NUM"));

고유 키(중복되지 않는)는 num
*/
// DTO의 내용은 DB에 저장 가능한 열이다.

public class J002_BoardDTO {
	// DB의 내용들을 여기 전부 포함시킨다.
	private int num;				// 고유 넘버, 7자리수, 공백X
	private String writer;			// 글쓴이, 12byte, 공백X
	private String subject;			// 글 제목, 500byte, 공백X
	private String pass;			// 패스워드, 10byte, 공백X
	private int readcount;			// 조회수, 5자리수, 기본값 0, 공백X
	private int ref;				// ref, 3자리수, 기본값 0, 공백X
	private int step;				// step, 3자리수, 기본값 0, 공백X
	private int depth;				// depth, 3자리수, 기본값 0, 공백X
	private Timestamp regdate;		// 날짜, timestamp, 기본값 sysdate, 공백X
	private String content;			// 내용, 4000byte, 공백X
	private String ip;				// IP 주소, 20byte, 공백X
	private int bn;					// 보드 넘버(게시판 분류), 2자리수, 공백X
	private String origin_filename;	// 원래 파일명, 256byte
	private String server_filename;	// 서버에올려진파일명, 256byte
	private int filesize;			// 파일의 크기, 7자리수
	private String filetype;		// 파일의 종류, 256byte
	private String preface;			// 머리말(잡담/KBO/질문), 50byte, 공백X
	private int mem;				// 멤버 상태(0,1,2), 1자리수, 기본값 0, 공백X
	
	public J002_BoardDTO() {
		// 생성자
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getBn() {
		return bn;
	}

	public void setBn(int bn) {
		this.bn = bn;
	}

	public String getOrigin_filename() {
		return origin_filename;
	}

	public void setOrigin_filename(String origin_filename) {
		this.origin_filename = origin_filename;
	}

	public String getServer_filename() {
		return server_filename;
	}

	public void setServer_filename(String server_filename) {
		this.server_filename = server_filename;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getPreface() {
		return preface;
	}

	public void setPreface(String preface) {
		this.preface = preface;
	}

	public int getMem() {
		return mem;
	}

	public void setMem(int mem) {
		this.mem = mem;
	}
	
	
}