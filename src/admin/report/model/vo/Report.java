package admin.report.model.vo;

import java.util.Date;

public class Report {
	private int bNo;			// 글번호
	private String bTtitle;		// 글제목
	private String bContent;	// 글내용
	private Date enrollDate;	// 작성일
	private Date modfiyDate;	// 수정일
	private int bCount;			// 조회수
	private String status;		// 게시 여부
	private int uNo;			// 작성자 번호			
	private String uNickName;	// 작성자 이름
	private int report;			// 신고횟수
	
	
	public Report() {
	}

	

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getbTtitle() {
		return bTtitle;
	}

	public void setbTtitle(String bTtitle) {
		this.bTtitle = bTtitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModfiyDate() {
		return modfiyDate;
	}

	public void setModfiyDate(Date modfiyDate) {
		this.modfiyDate = modfiyDate;
	}

	public int getbCount() {
		return bCount;
	}

	public void setbCount(int bCount) {
		this.bCount = bCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getuNo() {
		return uNo;
	}

	public void setuNo(int uNo) {
		this.uNo = uNo;
	}

	public String getuNickName() {
		return uNickName;
	}

	public void setuNickName(String uNickName) {
		this.uNickName = uNickName;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

	@Override
	public String toString() {
		return "Report [bNo=" + bNo + ", bTtitle=" + bTtitle + ", bContent=" + bContent + ", enrollDate=" + enrollDate
				+ ", modfiyDate=" + modfiyDate + ", bCount=" + bCount + ", status=" + status + ", uNo=" + uNo
				+ ", uNickName=" + uNickName + ", report=" + report + "]";
	}
	
}
