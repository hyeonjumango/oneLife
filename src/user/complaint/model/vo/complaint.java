package user.complaint.model.vo;

import java.util.Date;
import java.util.List;

public class complaint {
	private int c_no;
	private String c_title;
	private String c_content;
	private Date enroll_date;
	private Date modify_date;
	private String open;
	private String status;
	private int u_no;
	private String u_id;
	private int r_dong;
	private int r_ho;
	private String r_name;
	private String r_nickName;
	private List<complaint_manager> replyList;   // 댓글
	private int cm_no;
	private int c_reply_count;
	
	/*
	C_NO	NUMBER		                                      건의합니다 번호
	C_TITLE	VARCHAR2(100 BYTE)		           제목
	C_CONTENT	VARCHAR2(4000 BYTE)		  작성내용
	ENROLL_DATE	DATE		                             등록날짜
	MODIFY_DATE	DATE		                             수정날짜
	OPEN	VARCHAR2(1 BYTE)	                    공개여부
	STATUS	VARCHAR2(1 BYTE)	                    작성여부
	U_NO	NUMBER	                                                회원번호
	*/
	
	public complaint () {}
	
	


	public complaint(int c_no, String c_title, String c_content, Date enroll_date, Date modify_date, String open,
			String status, int u_no, String u_id, int r_dong, int r_ho, String r_name, int c_reply_count) {
		super();
		this.c_no = c_no;
		this.c_title = c_title;
		this.c_content = c_content;
		this.enroll_date = enroll_date;
		this.modify_date = modify_date;
		this.open = open;
		this.status = status;
		this.u_no = u_no;
		this.u_id = u_id;
		this.r_dong = r_dong;
		this.r_ho = r_ho;
		this.r_name = r_name;
		this.c_reply_count = c_reply_count;
	}




	public complaint(int c_no, String c_title, String c_content, Date enroll_date, Date modify_date, String open,
			String status, int u_no, String u_id, int r_dong, int r_ho, String r_name,
			List<complaint_manager> replyList, int cm_no) {
		super();
		this.c_no = c_no;
		this.c_title = c_title;
		this.c_content = c_content;
		this.enroll_date = enroll_date;
		this.modify_date = modify_date;
		this.open = open;
		this.status = status;
		this.u_no = u_no;
		this.u_id = u_id;
		this.r_dong = r_dong;
		this.r_ho = r_ho;
		this.r_name = r_name;
		this.replyList = replyList;
		this.cm_no = cm_no;
	}



	public complaint(int c_no, String c_title, String c_content, Date enroll_date, Date modify_date, String open,
			String status, int u_no, String u_id, int r_dong, int r_ho, String r_name) {
		super();
		this.c_no = c_no;
		this.c_title = c_title;
		this.c_content = c_content;
		this.enroll_date = enroll_date;
		this.modify_date = modify_date;
		this.open = open;
		this.status = status;
		this.u_no = u_no;
		this.u_id = u_id;
		this.r_dong = r_dong;
		this.r_ho = r_ho;
		this.r_name = r_name;
	}

//
//	public complaint(int c_no, String c_title, String c_content, Date enroll_date, Date modify_date, String open,
//			String status, int u_no, String u_id, int r_dong, int r_ho, String r_name, int cm_no) {
//		super();
//		this.c_no = c_no;
//		this.c_title = c_title;
//		this.c_content = c_content;
//		this.enroll_date = enroll_date;
//		this.modify_date = modify_date;
//		this.open = open;
//		this.status = status;
//		this.u_no = u_no;
//		this.u_id = u_id;
//		this.r_dong = r_dong;
//		this.r_ho = r_ho;
//		this.r_name = r_name;
//		this.cm_no = cm_no;
//	}


	public complaint(String c_title, String c_content, String open, int u_no) {
		super();
		this.c_title = c_title;
		this.c_content = c_content;
		this.open = open;
		this.u_no = u_no;
	}
	
	public complaint(int c_no, String c_title, String c_content, String open) {
		super();
		this.c_no = c_no;
		this.c_title = c_title;
		this.c_content = c_content;
		this.open = open;
	}

	



	public complaint(int c_no, String c_title, String c_content, Date modify_date, String u_id, String r_name) {
		super();
		this.c_no = c_no;
		this.c_title = c_title;
		this.c_content = c_content;
		this.modify_date = modify_date;
		this.u_id = u_id;
		this.r_name = r_name;
	}





	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public String getC_title() {
		return c_title;
	}

	public void setC_title(String c_title) {
		this.c_title = c_title;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
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

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getU_no() {
		return u_no;
	}

	public void setU_no(int u_no) {
		this.u_no = u_no;
	}
	

	public String getU_id() {
		return u_id;
	}



	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	
	



	public int getR_dong() {
		return r_dong;
	}

	public String getR_nickName() {
		return r_nickName;
	}

	public void setR_nickName(String r_nickName) {
		this.r_nickName = r_nickName;
	}




	public void setR_dong(int r_dong) {
		this.r_dong = r_dong;
	}





	public int getR_ho() {
		return r_ho;
	}





	public void setR_ho(int r_ho) {
		this.r_ho = r_ho;
	}





	public String getR_name() {
		return r_name;
	}





	public void setR_name(String r_name) {
		this.r_name = r_name;
	}


	



	public List<complaint_manager> getReplyList() {
		return replyList;
	}





	public void setReplyList(List<complaint_manager> replyList) {
		this.replyList = replyList;
	}
	
	





	public int getCm_no() {
		return cm_no;
	}


	public void setCm_no(int cm_no) {
		this.cm_no = cm_no;
	}
	
	


	public int getC_reply_count() {
		return c_reply_count;
	}


	public void setC_reply_count(int c_reply_count) {
		this.c_reply_count = c_reply_count;
	}


	@Override
	public String toString() {
		return "complaint [c_no=" + c_no + ", c_title=" + c_title + ", c_content=" + c_content + ", enroll_date="
				+ enroll_date + ", modify_date=" + modify_date + ", open=" + open + ", status=" + status + ", u_no="
				+ u_no + ", u_id=" + u_id + ", r_dong=" + r_dong + ", r_ho=" + r_ho + ", r_name=" + r_name
				+ ", replyList=" + replyList + ", cm_no=" + cm_no + ", c_reply_count=" + c_reply_count + "]";
	}



	





	






	
	
	

}
