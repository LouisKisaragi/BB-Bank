package bb.admin.dto;

import java.sql.Date;

public class PointManagerDTO { 
	/* 관리자가 포인트 입차감 뿐만 아니라 전체 관리를 위한 DTO로 포인트 설계가 끝나면 조정할 것.
	다만, 이 코드는 유저의 행동(글 작성)을 확인 후 관리자 계정으로 직접 지급하고
	오프라인에서 모은 포인트를 사용했다면 관리자가 직접 차감하는 방식이었다.
	포인트 지급 및 차감에 관한 알고리즘은 adminDAO에 있으니 확인하고, 반드시 자동 지급방식으로 수정 할 것.
	다만 코드 작성방식은 반드시 기존 코드에 맞게 일부분만 조정할 것,
	포인트 설계가 끝나면 이 주석은 삭제하고 포인트 설계에 관한 설명으로 수정바람.
	포인트
	최초 가입 : 100, 로그인 & 글 작성 : 10, 댓글 : 5, 투표(승부예측) : 50 */
	
		private String member_id;
		private int p_calcul;
		private String p_cont;
		private Date p_date;
		
		public PointManagerDTO () {}

		public PointManagerDTO(String member_id, int p_calcul, String p_cont, Date p_date) {
			super();
			this.member_id = member_id;
			this.p_calcul = p_calcul;
			this.p_cont = p_cont;
			this.p_date = p_date;
		}

		public String getMember_id() {
			return member_id;
		}

		public void setMember_id(String member_id) {
			this.member_id = member_id;
		}

		public int getP_calcul() {
			return p_calcul;
		}

		public void setP_calcul(int p_calcul) {
			this.p_calcul = p_calcul;
		}

		public String getP_cont() {
			return p_cont;
		}

		public void setP_cont(String p_cont) {
			this.p_cont = p_cont;
		}

		public Date getP_date() {
			return p_date;
		}

		public void setP_date(Date p_date) {
			this.p_date = p_date;
		}
	}

