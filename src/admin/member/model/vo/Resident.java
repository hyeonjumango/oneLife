package admin.member.model.vo;

import java.util.Date;

public class Resident {
	/*
	R_NO	NUMBER
	R_DONG	NUMBER
	R_HO	NUMBER
	R_NAME	VARCHAR2(10 BYTE)
	R_EMAIL	VARCHAR2(30 BYTE)
	R_TYPE	VARCHAR2(9 BYTE)
	R_STATUS	CHAR(1 BYTE)
	R_DATE	DATE
	*/
	private int rNo;
	private int uNo;
	private int rDong;
	private int rHo;
	private String uId;
	private String rName;
	private String uNickName;
	private String uPhone;
	private String rEmail;
	private String rType;
	private String rStatus;
	private Date rDate;
	
	public Resident() {
		super();
	}
	

	public Resident(int rDong, int rHo, String rName, String rEmail) {
		super();
		this.rDong = rDong;
		this.rHo = rHo;
		this.rName = rName;
		this.rEmail = rEmail;
	}

	public Resident(int rDong, int rHo, String rName, String rEmail, String rType) {
		super();
		this.rDong = rDong;
		this.rHo = rHo;
		this.rName = rName;
		this.rEmail = rEmail;
		this.rType = rType;
	}
	
	


	public Resident(int rDong, int rHo) {
		super();
		this.rDong = rDong;
		this.rHo = rHo;
	}


	public int getrNo() {
		return rNo;
	}


	public void setrNo(int rNo) {
		this.rNo = rNo;
	}


	public int getuNo() {
		return uNo;
	}


	public void setuNo(int uNo) {
		this.uNo = uNo;
	}


	public int getrDong() {
		return rDong;
	}


	public void setrDong(int rDong) {
		this.rDong = rDong;
	}


	public int getrHo() {
		return rHo;
	}


	public void setrHo(int rHo) {
		this.rHo = rHo;
	}


	public String getuId() {
		return uId;
	}


	public void setuId(String uId) {
		this.uId = uId;
	}


	public String getrName() {
		return rName;
	}


	public void setrName(String rName) {
		this.rName = rName;
	}


	public String getuNickName() {
		return uNickName;
	}


	public void setuNickName(String uNickName) {
		this.uNickName = uNickName;
	}


	public String getuPhone() {
		return uPhone;
	}


	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}


	public String getrEmail() {
		return rEmail;
	}


	public void setrEmail(String rEmail) {
		this.rEmail = rEmail;
	}


	public String getrType() {
		return rType;
	}


	public void setrType(String rType) {
		this.rType = rType;
	}


	public String getrStatus() {
		return rStatus;
	}


	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}


	public Date getrDate() {
		return rDate;
	}


	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}


	@Override
	public String toString() {
		return "Resident [rNo=" + rNo + ", uNo=" + uNo + ", rDong=" + rDong + ", rHo=" + rHo + ", uId=" + uId
				+ ", rName=" + rName + ", uNickName=" + uNickName + ", uPhone=" + uPhone + ", rEmail=" + rEmail
				+ ", rType=" + rType + ", rStatus=" + rStatus + ", rDate=" + rDate + "]";
	}

}	

	
