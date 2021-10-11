package user.board.model.vo;

import java.util.Date;

public class Board_Like {
	
	private int u_no;
	private int b_no;
	private Date bl__date;
	private String bl_status;
	private int bl_no;
	private int b_likeUsercnt;
	
	/*U_NO	NUMBER
		B_NO	NUMBER
		BL_DATE	DATE
		BL_STATUS	VARCHAR2(1 BYTE)
	 */
	
	public Board_Like() {}

	public Board_Like(int u_no, int b_no, Date bl__date, String bl_status) {
		super();
		this.u_no = u_no;
		this.b_no = b_no;
		this.bl__date = bl__date;
		this.bl_status = bl_status;
	}
	
	

	public Board_Like(int b_likeUsercnt) {
		super();
		this.b_likeUsercnt = b_likeUsercnt;
	}

	public int getU_no() {
		return u_no;
	}

	public void setU_no(int u_no) {
		this.u_no = u_no;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public Date getBl__date() {
		return bl__date;
	}

	public void setBl__date(Date bl__date) {
		this.bl__date = bl__date;
	}

	public String getBl_status() {
		return bl_status;
	}

	public void setBl_status(String bl_status) {
		this.bl_status = bl_status;
	}
	
	

	public int getBl_no() {
		return bl_no;
	}

	public void setBl_no(int bl_no) {
		this.bl_no = bl_no;
	}
	
	

	public int getB_likeUsercnt() {
		return b_likeUsercnt;
	}

	public void setB_likeUsercnt(int b_likeUsercnt) {
		this.b_likeUsercnt = b_likeUsercnt;
	}

	@Override
	public String toString() {
		return "Board_Like [u_no=" + u_no + ", b_no=" + b_no + ", bl__date=" + bl__date + ", bl_status=" + bl_status
				+ ", bl_no=" + bl_no + ", b_likeUsercnt=" + b_likeUsercnt + "]";
	}

	

	
	
	

}
