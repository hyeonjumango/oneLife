package user.VisitCar.model.vo;

import java.util.Date;

public class VisitCar {
	
	private int VC_ID;
	private Date VC_DATE;
	private String VC_NO;
	private String VC_PURPOSE;
	private String VC_PHONE;
	private Date VC_ENROLLDATE;
	private Date VC_MODIFYDATE;
	private String VC_STATUS;
	private String R_NAME;
	private int R_DONG;
	private int R_HO;
	private int U_NO;
	private String mName;
	
	public VisitCar() {}

	public VisitCar(int vC_ID, Date vC_DATE, String vC_NO, String vC_PURPOSE, String vC_PHONE, Date vC_ENROLLDATE,
			Date vC_MODIFYDATE, String vC_STATUS, String r_NAME, int u_NO) {
		super();
		VC_ID = vC_ID;
		VC_DATE = vC_DATE;
		VC_NO = vC_NO;
		VC_PURPOSE = vC_PURPOSE;
		VC_PHONE = vC_PHONE;
		VC_ENROLLDATE = vC_ENROLLDATE;
		VC_MODIFYDATE = vC_MODIFYDATE;
		VC_STATUS = vC_STATUS;
		R_NAME = r_NAME;
		U_NO = u_NO;
	}
	
	public VisitCar(int vC_ID, Date vC_DATE, String vC_NO, String vC_PURPOSE, String vC_PHONE, Date vC_MODIFYDATE,
			String vC_STATUS, String r_NAME, int u_NO, String m_name) {
		super();
		VC_ID = vC_ID;
		VC_DATE = vC_DATE;
		VC_NO = vC_NO;
		VC_PURPOSE = vC_PURPOSE;
		VC_PHONE = vC_PHONE;
		VC_MODIFYDATE = vC_MODIFYDATE;
		VC_STATUS = vC_STATUS;
		R_NAME = r_NAME;
		U_NO = u_NO;
		mName = m_name;
	}

	public VisitCar(int vC_ID, Date vC_DATE, String vC_NO, String vC_PURPOSE, String vC_PHONE) {
		super();
		VC_ID = vC_ID;
		VC_DATE = vC_DATE;
		VC_NO = vC_NO;
		VC_PURPOSE = vC_PURPOSE;
		VC_PHONE = vC_PHONE;
	}

	public VisitCar(int vC_ID, Date vC_DATE, String vC_NO, String vC_PURPOSE, String vC_PHONE, Date vC_ENROLLDATE,
			Date vC_MODIFYDATE, String vC_STATUS, String r_NAME, int r_DONG, int r_HO, int u_NO) {
		super();
		VC_ID = vC_ID;
		VC_DATE = vC_DATE;
		VC_NO = vC_NO;
		VC_PURPOSE = vC_PURPOSE;
		VC_PHONE = vC_PHONE;
		VC_ENROLLDATE = vC_ENROLLDATE;
		VC_MODIFYDATE = vC_MODIFYDATE;
		VC_STATUS = vC_STATUS;
		R_NAME = r_NAME;
		R_DONG = r_DONG;
		R_HO = r_HO;
		U_NO = u_NO;
	}

	public VisitCar(int vC_ID, Date vC_DATE, String vC_NO, String vC_PURPOSE, String vC_PHONE, Date vC_MODIFYDATE,
			String vC_STATUS, String r_NAME, int r_DONG, int r_HO, int u_NO, String m_name) {
		super();
		VC_ID = vC_ID;
		VC_DATE = vC_DATE;
		VC_NO = vC_NO;
		VC_PURPOSE = vC_PURPOSE;
		VC_PHONE = vC_PHONE;
		VC_MODIFYDATE = vC_MODIFYDATE;
		VC_STATUS = vC_STATUS;
		R_NAME = r_NAME;
		R_DONG = r_DONG;
		R_HO = r_HO;
		U_NO = u_NO;
		mName = m_name;
	}

	public int getVC_ID() {
		return VC_ID;
	}

	public void setVC_ID(int vC_ID) {
		VC_ID = vC_ID;
	}

	public Date getVC_DATE() {
		return VC_DATE;
	}

	public void setVC_DATE(Date vC_DATE) {
		VC_DATE = vC_DATE;
	}

	public String getVC_NO() {
		return VC_NO;
	}

	public void setVC_NO(String vC_NO) {
		VC_NO = vC_NO;
	}

	public String getVC_PURPOSE() {
		return VC_PURPOSE;
	}

	public void setVC_PURPOSE(String vC_PURPOSE) {
		VC_PURPOSE = vC_PURPOSE;
	}

	public String getVC_PHONE() {
		return VC_PHONE;
	}

	public void setVC_PHONE(String vC_PHONE) {
		VC_PHONE = vC_PHONE;
	}

	public Date getVC_ENROLLDATE() {
		return VC_ENROLLDATE;
	}

	public void setVC_ENROLLDATE(Date vC_ENROLLDATE) {
		VC_ENROLLDATE = vC_ENROLLDATE;
	}

	public Date getVC_MODIFYDATE() {
		return VC_MODIFYDATE;
	}

	public void setVC_MODIFYDATE(Date vC_MODIFYDATE) {
		VC_MODIFYDATE = vC_MODIFYDATE;
	}

	public String getVC_STATUS() {
		return VC_STATUS;
	}

	public void setVC_STATUS(String vC_STATUS) {
		VC_STATUS = vC_STATUS;
	}

	public String getR_NAME() {
		return R_NAME;
	}

	public void setR_NAME(String r_NAME) {
		R_NAME = r_NAME;
	}

	public int getR_DONG() {
		return R_DONG;
	}

	public void setR_DONG(int r_DONG) {
		R_DONG = r_DONG;
	}

	public int getR_HO() {
		return R_HO;
	}

	public void setR_HO(int r_HO) {
		R_HO = r_HO;
	}

	public int getU_NO() {
		return U_NO;
	}

	public void setU_NO(int u_NO) {
		U_NO = u_NO;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	@Override
	public String toString() {
		return "VisitCar [VC_ID=" + VC_ID + ", VC_DATE=" + VC_DATE + ", VC_NO=" + VC_NO + ", VC_PURPOSE=" + VC_PURPOSE
				+ ", VC_PHONE=" + VC_PHONE + ", VC_ENROLLDATE=" + VC_ENROLLDATE + ", VC_MODIFYDATE=" + VC_MODIFYDATE
				+ ", VC_STATUS=" + VC_STATUS + ", R_NAME=" + R_NAME + ", R_DONG=" + R_DONG + ", R_HO=" + R_HO
				+ ", U_NO=" + U_NO + ", mName=" + mName + "]";
	}

}
