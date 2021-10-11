package user.notice.model.vo;

import java.util.Date;

public class Notice {
	private int n_no;
	private String n_title;
	private String n_content;
	private int n_count;
	private Date enroll_date;
	private Date modify_date;
	private String status;
	private int m_no;
	private String m_nick;
	private int fileCount; // 파일이있는지없는지여부
	
	/*
	N_NO	NUMBER                        공지사항 번호
	N_TITLE	VARCHAR2(100 BYTE)            제목
	N_CONTENT	VARCHAR2(4000 BYTE)       작성내용
	N_COUNT	NUMBER                        조회수
	ENROLL_DATE	DATE                      등록날짜
	MODIFY_DATE	DATE                      수정날짜
	STATUS	VARCHAR2(1 BYTE)              작성여부
	M_NO	NUMBER                        관리자 번호
	*/
		
		
	public Notice () {}
	
	
	
	public Notice(int n_no, String n_title, String n_content, int n_count, Date enroll_date, Date modify_date,
			String status, int m_no, String m_nick) {
		super();
		this.n_no = n_no;
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_count = n_count;
		this.enroll_date = enroll_date;
		this.modify_date = modify_date;
		this.status = status;
		this.m_no = m_no;
		this.m_nick = m_nick;
	}
	

	public Notice(int n_no, String n_title, String n_content) {
		super();
		this.n_no = n_no;
		this.n_title = n_title;
		this.n_content = n_content;
	}



	public Notice(String n_title, String n_content, int m_no) {
		super();
		this.n_title = n_title;
		this.n_content = n_content;
		this.m_no = m_no;
	}



	public int getN_no() {
		return n_no;
	}
	
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	
	public String getN_title() {
		return n_title;
	}
	
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	
	public String getN_content() {
		return n_content;
	}
	
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	
	public int getN_count() {
		return n_count;
	}
	
	public void setN_count(int n_count) {
		this.n_count = n_count;
	}
	
	public Date getEnroll_date() {
		return enroll_date;
	}
	
	public void setEnroll_date(Date enroll_date) {
		this.enroll_date = enroll_date;
	}
	
	public Date getModify_date() {
		return modify_date;
	}
	
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getM_no() {
		return m_no;
	}
	
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	
	
	
	public String getM_nick() {
		return m_nick;
	}



	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
	

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}



	@Override
	public String toString() {
		return "Notice [n_no=" + n_no + ", n_title=" + n_title + ", n_content=" + n_content + ", n_count=" + n_count
				+ ", enroll_date=" + enroll_date + ", modify_date=" + modify_date + ", status=" + status + ", m_no="
				+ m_no + ", m_nick=" + m_nick + "]";
	}



	
}
