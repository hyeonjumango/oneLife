package user.complaint.model.vo;

import java.util.Date;

public class complaint_manager {
	private int cm_no;
	private int c_no;
	private String cm_content;
	private Date cm_enroll_date;
	private Date cm_modify_date;
	private String cm_status;
	private int m_no;
	private String m_nick;
	
	/*
	 * CM_NO	NUMBER		                                    건의합니다(관리자) 번호
		C_NO	NUMBER		                                    건의합니다(게시글) 번호
		CM_CONTENT	VARCHAR2(1900 BYTE)		댓글내용
		ENROLL_DATE	DATE		                           등록날짜
		MODIFY_DATE	DATE		                           수정날짜
		STATUS	VARCHAR2(1 BYTE)		         작성여부
		M_NO	NUMBER		                                     관리자 번호
	 * */
	
	public complaint_manager() {}

	
	


	public complaint_manager(int cm_no, int c_no, String cm_content, Date cm_enroll_date, Date cm_modify_date,
			String cm_status, int m_no, String m_nick) {
		super();
		this.cm_no = cm_no;
		this.c_no = c_no;
		this.cm_content = cm_content;
		this.cm_enroll_date = cm_enroll_date;
		this.cm_modify_date = cm_modify_date;
		this.cm_status = cm_status;
		this.m_no = m_no;
		this.m_nick = m_nick;
	}



	public complaint_manager(int cm_no, int c_no, String cm_content, Date cm_enroll_date, Date cm_modify_date,
			String cm_status, int m_no) {
		super();
		this.cm_no = cm_no;
		this.c_no = c_no;
		this.cm_content = cm_content;
		this.cm_enroll_date = cm_enroll_date;
		this.cm_modify_date = cm_modify_date;
		this.cm_status = cm_status;
		this.m_no = m_no;
	}





	public int getCm_no() {
		return cm_no;
	}


	public void setCm_no(int cm_no) {
		this.cm_no = cm_no;
	}


	public int getC_no() {
		return c_no;
	}


	public void setC_no(int c_no) {
		this.c_no = c_no;
	}


	public String getCm_content() {
		return cm_content;
	}


	public void setCm_content(String cm_content) {
		this.cm_content = cm_content;
	}


	public Date getCm_enroll_date() {
		return cm_enroll_date;
	}


	public void setCm_enroll_date(Date cm_enroll_date) {
		this.cm_enroll_date = cm_enroll_date;
	}


	public Date getCm_modify_date() {
		return cm_modify_date;
	}


	public void setCm_modify_date(Date cm_modify_date) {
		this.cm_modify_date = cm_modify_date;
	}


	public String getCm_status() {
		return cm_status;
	}


	public void setCm_status(String cm_status) {
		this.cm_status = cm_status;
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





	@Override
	public String toString() {
		return "complaint_manager [cm_no=" + cm_no + ", c_no=" + c_no + ", cm_content=" + cm_content
				+ ", cm_enroll_date=" + cm_enroll_date + ", cm_modify_date=" + cm_modify_date + ", cm_status="
				+ cm_status + ", m_no=" + m_no + ", m_nick=" + m_nick + "]";
	}






	
	
}
