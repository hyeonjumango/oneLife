package admin.report.model.vo;

import java.util.Date;

public class Reply {
	private int bNo;					// 게시글 번호
	private String uNickname;			// 게시글 작성자 이름
	private String bTitle;				// 게시글 제목
	private String bContent;			// 게시글 내용
	private int commentBcNo;			// 댓글 번호
	private Date commentEnrollDate;		// 댓글 작성일
	private String bcContent;	   		// 댓글 내용
	private int commentuNo;				// 댓글 작성자 번호
	private String commentuNickname;	// 댓글 작성자 이름
	private int report;					// 댓글 신고 횟수
	private String commentStatus;		// 댓글 게시 여부
	
	public Reply() {
		super();
	}

	public Reply(int bNo, String uNickname, String bTitle, String bContent, int commentBcNo, Date commentEnrollDate,
			String bcContent, int commentuNo, String commentuNickname, int report, String commentStatus) {
		super();
		this.bNo = bNo;
		this.uNickname = uNickname;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.commentBcNo = commentBcNo;
		this.commentEnrollDate = commentEnrollDate;
		this.bcContent = bcContent;
		this.commentuNo = commentuNo;
		this.commentuNickname = commentuNickname;
		this.report = report;
		this.commentStatus = commentStatus;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getuNickname() {
		return uNickname;
	}

	public void setuNickname(String uNickname) {
		this.uNickname = uNickname;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public int getCommentBcNo() {
		return commentBcNo;
	}

	public void setCommentBcNo(int commentBcNo) {
		this.commentBcNo = commentBcNo;
	}

	public String getBcContent() {
		return bcContent;
	}

	public void setBcContent(String bcContent) {
		this.bcContent = bcContent;
	}

	public int getCommentuNo() {
		return commentuNo;
	}

	public void setCommentuNo(int commentuNo) {
		this.commentuNo = commentuNo;
	}

	public String getCommentuNickname() {
		return commentuNickname;
	}

	public void setCommentuNickname(String commentuNickname) {
		this.commentuNickname = commentuNickname;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}
	
	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}
	
	

	public Date getCommentEnrollDate() {
		return commentEnrollDate;
	}

	public void setCommentEnrollDate(Date commentEnrollDate) {
		this.commentEnrollDate = commentEnrollDate;
	}

	@Override
	public String toString() {
		return "Reply [bNo=" + bNo + ", uNickname=" + uNickname + ", bTitle=" + bTitle + ", bContent=" + bContent
				+ ", commentBcNo=" + commentBcNo + ", commentEnrollDate=" + commentEnrollDate + ", bcContent="
				+ bcContent + ", commentuNo=" + commentuNo + ", commentuNickname=" + commentuNickname + ", report="
				+ report + ", commentStatus=" + commentStatus + "]";
	}

	
}
