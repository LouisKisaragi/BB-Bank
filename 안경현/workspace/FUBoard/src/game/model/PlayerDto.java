package game.model;

public class PlayerDto {
	private int votenum;
	private String votenick;
	private String voteid;
	private int votegamenum;
	private String voteteam;
	private String votecomment;
	public int getVotenum() {
		return votenum;
	}
	public void setVotenum(int votenum) {
		this.votenum = votenum;
	}
	public String getVotenick() {
		return votenick;
	}
	public void setVotenick(String votenick) {
		this.votenick = votenick;
	}
	public String getVoteid() {
		return voteid;
	}
	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}
	public int getVotegamenum() {
		return votegamenum;
	}
	public void setVotegamenum(int votegamenum) {
		this.votegamenum = votegamenum;
	}
	public String getVoteteam() {
		return voteteam;
	}
	public void setVoteteam(String voteteam) {
		this.voteteam = voteteam;
	}
	public String getVotecomment() {
		return votecomment;
	}
	public void setVotecomment(String votecomment) {
		this.votecomment = votecomment;
	}
	
}
