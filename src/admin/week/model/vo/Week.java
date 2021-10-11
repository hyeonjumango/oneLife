package admin.week.model.vo;

import java.sql.Date;

public class Week {
	/*
	 * SC_NO	NUMBER
		SC_OPEN_DATE	DATE
		SC_TITLE	VARCHAR2(100 BYTE)
		SC_CONTENT	VARCHAR2(900 BYTE)
		SC_IN_DATE	DATE
		SC_STATUS	CHAR(1 BYTE)
		SC_CATE_CODE	VARCHAR2(10 BYTE)
	 * */
	private int scNo;
	private Date scOpenDate;
	private Date scEndDate;
	private String scTitle;
	private String scContent;
	private Date inDate;
	private char scStatus;
	private String scCateCode;
	private String scCateName;
	private int nno;
	
	
	public Week() {
		super();
	}
	
	public Date getScEndDate() {
		return scEndDate;
	}

	public void setScEndDate(Date scEndDate) {
		this.scEndDate = scEndDate;
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public int getScNo() {
		return scNo;
	}

	public void setScNo(int scNo) {
		this.scNo = scNo;
	}

	public Date getScOpenDate() {
		return scOpenDate;
	}

	public void setScOpenDate(Date scOpenDate) {
		this.scOpenDate = scOpenDate;
	}

	public String getScTitle() {
		return scTitle;
	}

	public void setScTitle(String scTitle) {
		this.scTitle = scTitle;
	}

	public String getScContent() {
		return scContent;
	}

	public void setScContent(String scContent) {
		this.scContent = scContent;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public char getScStatus() {
		return scStatus;
	}

	public void setScStatus(char scStatus) {
		this.scStatus = scStatus;
	}

	public String getScCateCode() {
		return scCateCode;
	}

	public void setScCateCode(String scCateCode) {
		this.scCateCode = scCateCode;
	}

	public String getScCateName() {
		return scCateName;
	}

	public void setScCateName(String scCateName) {
		this.scCateName = scCateName;
	}

	@Override
	public String toString() {
		return "Week [scNo=" + scNo + ", scOpenDate=" + scOpenDate + ", scEndDate=" + scEndDate + ", scTitle=" + scTitle
				+ ", scContent=" + scContent + ", inDate=" + inDate + ", scStatus=" + scStatus + ", scCateCode="
				+ scCateCode + ", scCateName=" + scCateName + ", nno=" + nno + "]";
	}

	
	
	
}
