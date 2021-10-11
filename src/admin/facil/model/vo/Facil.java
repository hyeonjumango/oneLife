package admin.facil.model.vo;

import java.util.Date;

public class Facil {
	private int fcNo;
	private String fcName;
	private int fcSeatNo;
	private String fcSeatType;
	private String uId;
	private String rName;
	private String uPhone;
	private Date faDate;
	private Date fcStart;
	private Date fcEnd;
	private String fcStatus;
	
	public Facil() {
		super();
	}

	public Facil(int fcNo, String fcName, int fcSeatNo, String fcSeatType, String uId, String rName, String uPhone,
			Date faDate, Date fcStart, Date fcEnd, String fcStatus) {
		super();
		this.fcNo = fcNo;
		this.fcName = fcName;
		this.fcSeatNo = fcSeatNo;
		this.fcSeatType = fcSeatType;
		this.uId = uId;
		this.rName = rName;
		this.uPhone = uPhone;
		this.faDate = faDate;
		this.fcStart = fcStart;
		this.fcEnd = fcEnd;
		this.fcStatus = fcStatus;
	}

	public int getFcNo() {
		return fcNo;
	}

	public void setFcNo(int fcNo) {
		this.fcNo = fcNo;
	}

	public String getFcName() {
		return fcName;
	}

	public void setFcName(String fcName) {
		this.fcName = fcName;
	}

	public int getFcSeatNo() {
		return fcSeatNo;
	}

	public void setFcSeatNo(int fcSeatNo) {
		this.fcSeatNo = fcSeatNo;
	}

	public String getFcSeatType() {
		return fcSeatType;
	}

	public void setFcSeatType(String fcSeatType) {
		this.fcSeatType = fcSeatType;
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

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public Date getFaDate() {
		return faDate;
	}

	public void setFaDate(Date faDate) {
		this.faDate = faDate;
	}

	public Date getFcStart() {
		return fcStart;
	}

	public void setFcStart(Date fcStart) {
		this.fcStart = fcStart;
	}

	public Date getFcEnd() {
		return fcEnd;
	}

	public void setFcEnd(Date fcEnd) {
		this.fcEnd = fcEnd;
	}

	public String getFcStatus() {
		return fcStatus;
	}

	public void setFcStatus(String fcStatus) {
		this.fcStatus = fcStatus;
	}

	@Override
	public String toString() {
		return "Facil [fcNo=" + fcNo + ", fcName=" + fcName + ", fcSeatNo=" + fcSeatNo + ", fcSeatType=" + fcSeatType
				+ ", uId=" + uId + ", rName=" + rName + ", uPhone=" + uPhone + ", faDate=" + faDate + ", fcStart="
				+ fcStart + ", fcEnd=" + fcEnd + ", fcStatus=" + fcStatus + "]";
	}
	
}
