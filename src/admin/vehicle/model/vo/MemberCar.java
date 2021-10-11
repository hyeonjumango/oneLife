package admin.vehicle.model.vo;

import java.util.Date;

public class MemberCar {
	
	private int mcId;
	private String mcNo;
	private Date mcEnrollDate;
	private Date mcModifyDate;
	private int mNo;
	private String cPhone;
	private String rName;
	private int rNo;
	
	
	public MemberCar() {}
	
	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}



	public String getcPhone() {
		return cPhone;
	}

	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public int getMcId() {
		return mcId;
	}

	public void setMcId(int mcId) {
		this.mcId = mcId;
	}

	public String getMcNo() {
		return mcNo;
	}

	public void setMcNo(String mcNo) {
		this.mcNo = mcNo;
	}

	public Date getMcEnrollDate() {
		return mcEnrollDate;
	}

	public void setMcEnrollDate(Date mcEnrollDate) {
		this.mcEnrollDate = mcEnrollDate;
	}

	public Date getMcModifyDate() {
		return mcModifyDate;
	}

	public void setMcModifyDate(Date mcModifyDate) {
		this.mcModifyDate = mcModifyDate;
	}


	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public MemberCar(int mcId, String mcNo, Date mcEnrollDate, Date mcModifyDate, int mNo, String cPhone,
			String rName) {
		super();
		this.mcId = mcId;
		this.mcNo = mcNo;
		this.mcEnrollDate = mcEnrollDate;
		this.mcModifyDate = mcModifyDate;
		this.mNo = mNo;
		this.cPhone = cPhone;
		this.rName = rName;
	}

	@Override
	public String toString() {
		return "MemberCar [mcId=" + mcId + ", mcNo=" + mcNo + ", mcEnrollDate=" + mcEnrollDate + ", mcModifyDate="
				+ mcModifyDate + ", mNo=" + mNo + ", cPhone=" + cPhone + ", rName=" + rName + "]";
	}

	

	




	

	

	
	
}
