package user.vote.vo;


/*import java.util.Date;*/



public class Vote {
	private int v_no;
	private String v_title;
	private String v_content;
	private int v_count;
	private String v_enroll_date;
	private String v_modify_date;
	private String v_status;
	private int m_no;
	private String m_nick;
	private String v_choice;
	private int ve_no;
	private String ve_choice1;
	private String ve_choice2;
	private String ve_choice3;
	private String ve_choice4;
	private String ve_choice5;
	private String r_type;
	private int u_nocount;
	
/*
   "V_NO"   NUMBER      NOT NULL,
   "V_TITLE"   VARCHAR2(100 BYTE)      NOT NULL,
   "V_CONTENT"   VARCHAR2(4000 BYTE)      NOT NULL,
   "V_COUNT"   NUMBER   DEFAULT 0   NULL,
   "V_ENROLL_DATE"   DATE   DEFAULT SYSDATE   NULL,
   "V_MODIFY_DATE"   DATE   DEFAULT SYSDATE   NULL,
   "V_STATUS"   VARCHAR2(1 BYTE)   DEFAULT 'Y'   NULL,
   "M_NO"   NUMBER      NOT NULL
*/
	public Vote() {}
	


	public Vote(String v_title, String v_content, String v_modify_date, int m_no, String v_choice) {
	super();
	this.v_title = v_title;
	this.v_content = v_content;
	this.v_modify_date = v_modify_date;
	this.m_no = m_no;
	this.v_choice = v_choice;
}



	public Vote(String r_type) {
		super();
		this.r_type = r_type;
	}



	public Vote(int v_no, String v_title, String v_content, int v_count, String v_enroll_date, String v_modify_date,
			String v_status, int m_no, String m_nick) {
		super();
		this.v_no = v_no;
		this.v_title = v_title;
		this.v_content = v_content;
		this.v_count = v_count;
		this.v_enroll_date = v_enroll_date;
		this.v_modify_date = v_modify_date;
		this.v_status = v_status;
		this.m_no = m_no;
		this.m_nick = m_nick;
	}



	public Vote(int v_no, String v_title, String v_content, int v_count, String v_enroll_date, String v_modify_date,
			String v_status, int m_no) {
		super();
		this.v_no = v_no;
		this.v_title = v_title;
		this.v_content = v_content;
		this.v_count = v_count;
		this.v_enroll_date = v_enroll_date;
		this.v_modify_date = v_modify_date;
		this.v_status = v_status;
		this.m_no = m_no;
	}




	public Vote(int v_no, String ve_choice1, String ve_choice2, String ve_choice3, String ve_choice4,
			String ve_choice5) {
		super();
		this.v_no = v_no;
		this.ve_choice1 = ve_choice1;
		this.ve_choice2 = ve_choice2;
		this.ve_choice3 = ve_choice3;
		this.ve_choice4 = ve_choice4;
		this.ve_choice5 = ve_choice5;
	}


	



	public Vote(int v_no, String v_title, String v_content, int v_count, String v_enroll_date, String v_modify_date,
			String v_status, int m_no, String m_nick, String v_choice, int ve_no, String ve_choice1, String ve_choice2,
			String ve_choice3, String ve_choice4, String ve_choice5) {
		super();
		this.v_no = v_no;
		this.v_title = v_title;
		this.v_content = v_content;
		this.v_count = v_count;
		this.v_enroll_date = v_enroll_date;
		this.v_modify_date = v_modify_date;
		this.v_status = v_status;
		this.m_no = m_no;
		this.m_nick = m_nick;
		this.v_choice = v_choice;
		this.ve_no = ve_no;
		this.ve_choice1 = ve_choice1;
		this.ve_choice2 = ve_choice2;
		this.ve_choice3 = ve_choice3;
		this.ve_choice4 = ve_choice4;
		this.ve_choice5 = ve_choice5;
	}



	public int getV_no() {
		return v_no;
	}



	public void setV_no(int v_no) {
		this.v_no = v_no;
	}



	public String getV_title() {
		return v_title;
	}



	public void setV_title(String v_title) {
		this.v_title = v_title;
	}



	public String getV_content() {
		return v_content;
	}



	public void setV_content(String v_content) {
		this.v_content = v_content;
	}



	public int getV_count() {
		return v_count;
	}



	public void setV_count(int v_count) {
		this.v_count = v_count;
	}



	public String getV_enroll_date() {
		return v_enroll_date;
	}



	public void setV_enroll_date(String v_enroll_date) {
		this.v_enroll_date = v_enroll_date;
	}



	public String getV_modify_date() {
		return v_modify_date;
	}



	public void setV_modify_date(String v_modify_date) {
		this.v_modify_date = v_modify_date;
	}



	public String getV_status() {
		return v_status;
	}



	public void setV_status(String v_status) {
		this.v_status = v_status;
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



	public String getV_choice() {
		return v_choice;
	}



	public void setV_choice(String v_choice) {
		this.v_choice = v_choice;
	}



	public String getVe_choice1() {
		return ve_choice1;
	}



	public void setVe_choice1(String ve_choice1) {
		this.ve_choice1 = ve_choice1;
	}



	public String getVe_choice2() {
		return ve_choice2;
	}



	public void setVe_choice2(String ve_choice2) {
		this.ve_choice2 = ve_choice2;
	}



	public String getVe_choice3() {
		return ve_choice3;
	}



	public void setVe_choice3(String ve_choice3) {
		this.ve_choice3 = ve_choice3;
	}



	public String getVe_choice4() {
		return ve_choice4;
	}



	public void setVe_choice4(String ve_choice4) {
		this.ve_choice4 = ve_choice4;
	}



	public String getVe_choice5() {
		return ve_choice5;
	}



	public void setVe_choice5(String ve_choice5) {
		this.ve_choice5 = ve_choice5;
	}
	
	



	public int getVe_no() {
		return ve_no;
	}



	public void setVe_no(int ve_no) {
		this.ve_no = ve_no;
	}

	
	


	public String getR_type() {
		return r_type;
	}



	public void setR_type(String r_type) {
		this.r_type = r_type;
	}
	
	



	public int getU_nocount() {
		return u_nocount;
	}



	public void setU_nocount(int u_nocount) {
		this.u_nocount = u_nocount;
	}



	@Override
	public String toString() {
		return "Vote [v_no=" + v_no + ", v_title=" + v_title + ", v_content=" + v_content + ", v_count=" + v_count
				+ ", v_enroll_date=" + v_enroll_date + ", v_modify_date=" + v_modify_date + ", v_status=" + v_status
				+ ", m_no=" + m_no + ", m_nick=" + m_nick + ", v_choice=" + v_choice + ", ve_no=" + ve_no
				+ ", ve_choice1=" + ve_choice1 + ", ve_choice2=" + ve_choice2 + ", ve_choice3=" + ve_choice3
				+ ", ve_choice4=" + ve_choice4 + ", ve_choice5=" + ve_choice5 + ", r_type=" + r_type + ", u_nocount="
				+ u_nocount + "]";
	}






	


	
	

	

	
}

