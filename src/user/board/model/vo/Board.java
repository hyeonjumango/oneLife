package user.board.model.vo;

import java.util.Date;
import java.util.List;

import user.complaint.model.vo.complaint_manager;

public class Board {
	private int b_no;
	private String b_title;
	private String b_content;
	private Date b_enroll_date;
	private Date b_modify_date;
	private int b_count;
	private String b_status;
	private int u_no;
	private String u_nickname;
	private int bc_no;
	private List<Board_Comment> replyList;
	private int b_reply_count;   // 댓글 수
	private int b_likecnt;   // 좋아요 수
	private int b_likeUsercnt;
	
	/*
	 * B_NO	NUMBER
		B_TITLE	VARCHAR2(100 BYTE)
		B_CONTENT	LONG
		B_ENROLL_DATE	DATE
		B_MODIFY_DATE	DATE
		B_COUNT	NUMBER
		B_STATUS	VARCHAR2(1 BYTE)
		U_NO	NUMBER*/
	
	public Board() {}


	

	

	public Board(int b_likecnt) {
		super();
		this.b_likecnt = b_likecnt;
	}





	public Board(int b_no, String b_title, String b_content, Date b_enroll_date, Date b_modify_date, int b_count,
			String b_status, int u_no, String u_nickname, int bc_no, int b_reply_count, int b_likecnt) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_enroll_date = b_enroll_date;
		this.b_modify_date = b_modify_date;
		this.b_count = b_count;
		this.b_status = b_status;
		this.u_no = u_no;
		this.u_nickname = u_nickname;
		this.bc_no = bc_no;
		this.b_reply_count = b_reply_count;
		this.b_likecnt = b_likecnt;
	}









	public Board(int b_no, String b_title, String b_content, Date b_enroll_date, Date b_modify_date, int b_count,
			String b_status, int u_no, String u_nickname, int b_reply_count, int b_likecnt) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_enroll_date = b_enroll_date;
		this.b_modify_date = b_modify_date;
		this.b_count = b_count;
		this.b_status = b_status;
		this.u_no = u_no;
		this.u_nickname = u_nickname;
		this.b_reply_count = b_reply_count;
		this.b_likecnt = b_likecnt;
	}






	public Board(int b_no, String b_title, String b_content, Date b_enroll_date, Date b_modify_date, int b_count,
			String b_status, int u_no, String u_nickname) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_enroll_date = b_enroll_date;
		this.b_modify_date = b_modify_date;
		this.b_count = b_count;
		this.b_status = b_status;
		this.u_no = u_no;
		this.u_nickname = u_nickname;
	}
	
	

	public Board(int b_no, String b_title, String b_content, Date b_enroll_date, Date b_modify_date, int b_count,
			String b_status, int u_no, String u_nickname, int bc_no, List<Board_Comment> replyList) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_enroll_date = b_enroll_date;
		this.b_modify_date = b_modify_date;
		this.b_count = b_count;
		this.b_status = b_status;
		this.u_no = u_no;
		this.u_nickname = u_nickname;
		this.bc_no = bc_no;
		this.replyList = replyList;
	}



	public Board(int b_no, String b_title, String b_content, Date b_enroll_date, Date b_modify_date, int b_count,
			String b_status, int u_no, String u_nickname, int b_reply_count) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_enroll_date = b_enroll_date;
		this.b_modify_date = b_modify_date;
		this.b_count = b_count;
		this.b_status = b_status;
		this.u_no = u_no;
		this.u_nickname = u_nickname;
		this.b_reply_count = b_reply_count;
	}





	public Board(String b_title, String b_content, int u_no) {
		super();
		this.b_title = b_title;
		this.b_content = b_content;
		this.u_no = u_no;
	}
	
	





	public Board(int b_no, String b_title, String b_content) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_content = b_content;
	}





	public int getB_no() {
		return b_no;
	}


	public void setB_no(int b_no) {
		this.b_no = b_no;
	}


	public String getB_title() {
		return b_title;
	}


	public void setB_title(String b_title) {
		this.b_title = b_title;
	}


	public String getB_content() {
		return b_content;
	}


	public void setB_content(String b_content) {
		this.b_content = b_content;
	}


	public Date getB_enroll_date() {
		return b_enroll_date;
	}


	public void setB_enroll_date(Date b_enroll_date) {
		this.b_enroll_date = b_enroll_date;
	}


	public Date getB_modify_date() {
		return b_modify_date;
	}


	public void setB_modify_date(Date b_modify_date) {
		this.b_modify_date = b_modify_date;
	}


	public int getB_count() {
		return b_count;
	}


	public void setB_count(int b_count) {
		this.b_count = b_count;
	}


	public String getB_status() {
		return b_status;
	}


	public void setB_status(String b_status) {
		this.b_status = b_status;
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





	public int getBc_no() {
		return bc_no;
	}





	public void setBc_no(int bc_no) {
		this.bc_no = bc_no;
	}
	
	





	public List<Board_Comment> getReplyList() {
		return replyList;
	}





	public void setReplyList(List<Board_Comment> replyList) {
		this.replyList = replyList;
	}
	
	









	public int getB_reply_count() {
		return b_reply_count;
	}





	public void setB_reply_count(int b_reply_count) {
		this.b_reply_count = b_reply_count;
	}
	
	
	




	public int getB_likecnt() {
		return b_likecnt;
	}





	public void setB_likecnt(int b_likecnt) {
		this.b_likecnt = b_likecnt;
	}

	
	




	public int getB_likeUsercnt() {
		return b_likeUsercnt;
	}






	public void setB_likeUsercnt(int b_likeUsercnt) {
		this.b_likeUsercnt = b_likeUsercnt;
	}






	@Override
	public String toString() {
		return "Board [b_no=" + b_no + ", b_title=" + b_title + ", b_content=" + b_content + ", b_enroll_date="
				+ b_enroll_date + ", b_modify_date=" + b_modify_date + ", b_count=" + b_count + ", b_status=" + b_status
				+ ", u_no=" + u_no + ", u_nickname=" + u_nickname + ", bc_no=" + bc_no + ", replyList=" + replyList
				+ ", b_reply_count=" + b_reply_count + ", b_likecnt=" + b_likecnt + ", b_likeUsercnt=" + b_likeUsercnt
				+ "]";
	}









	





	















	

}
