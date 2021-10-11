package user.board.model.vo;

import java.util.Date;

public class Board_Comment {
	private int bc_no;
	private int b_no;
	private String bc_content;
	private Date bc_enroll_date;
	private Date bc_modify_date;
	private String bc_status;
	private int u_no;
	private String u_nickname;
	private int b_replay_count;
	
	/*
	 * BC_NO	NUMBER
		B_NO	NUMBER
		BC_CONTENT	VARCHAR2(1900 BYTE)
		BC_ENROLL_DATE	DATE
		BC_MODIFY_DATE	DATE
		BC_STATUS	VARCHAR2(1 BYTE)
		U_NO	NUMBER*/
	
	public Board_Comment() {}

	public Board_Comment(int bc_no, int b_no, String bc_content, Date bc_enroll_date, Date bc_modify_date,
			String bc_status, int u_no) {
		super();
		this.bc_no = bc_no;
		this.b_no = b_no;
		this.bc_content = bc_content;
		this.bc_enroll_date = bc_enroll_date;
		this.bc_modify_date = bc_modify_date;
		this.bc_status = bc_status;
		this.u_no = u_no;
	}
	
	

	public Board_Comment(int bc_no, int b_no, String bc_content, Date bc_enroll_date, Date bc_modify_date,
			String bc_status, int u_no, String u_nickname) {
		super();
		this.bc_no = bc_no;
		this.b_no = b_no;
		this.bc_content = bc_content;
		this.bc_enroll_date = bc_enroll_date;
		this.bc_modify_date = bc_modify_date;
		this.bc_status = bc_status;
		this.u_no = u_no;
		this.u_nickname = u_nickname;
	}
	
	
	

	public Board_Comment(int b_replay_count) {
		super();
		this.b_replay_count = b_replay_count;
	}

	public int getB_replay_count() {
		return b_replay_count;
	}

	public void setB_replay_count(int b_replay_count) {
		this.b_replay_count = b_replay_count;
	}

	

	public int getBc_no() {
		return bc_no;
	}

	public void setBc_no(int bc_no) {
		this.bc_no = bc_no;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getBc_content() {
		return bc_content;
	}

	public void setBc_content(String bc_content) {
		this.bc_content = bc_content;
	}

	public Date getBc_enroll_date() {
		return bc_enroll_date;
	}

	public void setBc_enroll_date(Date bc_enroll_date) {
		this.bc_enroll_date = bc_enroll_date;
	}

	public Date getBc_modify_date() {
		return bc_modify_date;
	}

	public void setBc_modify_date(Date bc_modify_date) {
		this.bc_modify_date = bc_modify_date;
	}

	public String getBc_status() {
		return bc_status;
	}

	public void setBc_status(String bc_status) {
		this.bc_status = bc_status;
	}

	public int getU_no() {
		return u_no;
	}

	public void setU_no(int u_no) {
		this.u_no = u_no;
	}
	
	

	public String getU_nickname() {
		return u_nickname;
	}

	public void setU_nickname(String u_nickname) {
		this.u_nickname = u_nickname;
	}

	@Override
	public String toString() {
		return "Board_Comment [bc_no=" + bc_no + ", b_no=" + b_no + ", bc_content=" + bc_content + ", bc_enroll_date="
				+ bc_enroll_date + ", bc_modify_date=" + bc_modify_date + ", bc_status=" + bc_status + ", u_no=" + u_no
				+ ", u_nickname=" + u_nickname + ", b_replay_count=" + b_replay_count + "]";
	}

	
	
	
	

}
