package user.member.model.vo;

import java.util.Date;

public class Member {
	private int U_NO;
	private String U_ID;
	private String U_NICKNAME;
	private String U_PW;
	private String U_PHONE;
	private Date REG_DATE;
	private Date MOD_DATE;
	private String U_STATUS;
	private int R_DONG;
	private int R_HO;
	private String R_NAME;
	private String R_EMAIL;
	private String R_type;
	private String R_STATUS;
	private Date R_DATE;
	private int R_NO;
	
	public Member() {}
	
	
	
	public Member(int r_DONG, int r_HO, String r_NAME, String r_EMAIL, String r_type, String r_STATUS, Date r_DATE,
			int r_NO) {
		super();
		R_DONG = r_DONG;
		R_HO = r_HO;
		R_NAME = r_NAME;
		R_EMAIL = r_EMAIL;
		R_type = r_type;
		R_STATUS = r_STATUS;
		R_DATE = r_DATE;
		R_NO = r_NO;
	}



	public Member(String u_ID, String r_NAME, String r_EMAIL) {
		super();
		U_ID = u_ID;
		R_NAME = r_NAME;
		R_EMAIL = r_EMAIL;
	}
	
	public Member(String r_NAME, String r_EMAIL) {
		super();
		R_NAME = r_NAME;
		R_EMAIL = r_EMAIL;
	}
	
	public Member(String u_ID) {
		U_ID = u_ID;
	}


	// 회원가입 객체
	public Member(String u_ID, String u_NICKNAME, String u_PW, String u_PHONE, String r_NAME, String r_EMAIL) {
		super();
		U_ID = u_ID;
		U_NICKNAME = u_NICKNAME;
		U_PW = u_PW;
		U_PHONE = u_PHONE;
		R_NAME = r_NAME;
		R_EMAIL = r_EMAIL;
	}
	
	
	public Member(String u_ID, String u_NICKNAME, String u_PW, String u_PHONE, int r_NO) {
		super();
		U_ID = u_ID;
		U_NICKNAME = u_NICKNAME;
		U_PW = u_PW;
		U_PHONE = u_PHONE;
		R_NO = r_NO;
	}
	
	// 회원정보 수정 객체
	public Member(int u_NO, String u_ID, String r_NAME,String u_NICKNAME, String u_PW,String u_PHONE, String r_EMAIL) {
		super();
		U_NO = u_NO;
		U_ID = u_ID;
		U_NICKNAME = u_NICKNAME;
		U_PW = u_PW;
		U_PHONE = u_PHONE;
		R_NAME = r_NAME;
		R_EMAIL = r_EMAIL;
	}
	
	public Member(int u_NO, String u_ID, String u_NICKNAME, String u_PW, String u_PHONE, Date rEG_DATE, Date mOD_DATE,
			String u_STATUS, int r_DONG, int r_HO, String r_NAME, String r_EMAIL, String r_type, String r_STATUS,
			Date r_DATE, int r_NO) {
		super();
		U_NO = u_NO;
		U_ID = u_ID;
		U_NICKNAME = u_NICKNAME;
		U_PW = u_PW;
		U_PHONE = u_PHONE;
		REG_DATE = rEG_DATE;
		MOD_DATE = mOD_DATE;
		U_STATUS = u_STATUS;
		R_DONG = r_DONG;
		R_HO = r_HO;
		R_NAME = r_NAME;
		R_EMAIL = r_EMAIL;
		R_type = r_type;
		R_STATUS = r_STATUS;
		R_DATE = r_DATE;
		R_NO = r_NO;
	}

	public int getU_NO() {
		return U_NO;
	}


	public void setU_NO(int u_NO) {
		U_NO = u_NO;
	}

	public String getU_ID() {
		return U_ID;
	}

	public void setU_ID(String u_ID) {
		U_ID = u_ID;
	}

	public String getU_NICKNAME() {
		return U_NICKNAME;
	}

	public void setU_NICKNAME(String u_NICKNAME) {
		U_NICKNAME = u_NICKNAME;
	}

	public String getU_PW() {
		return U_PW;
	}

	public void setU_PW(String u_PW) {
		U_PW = u_PW;
	}

	public String getU_PHONE() {
		return U_PHONE;
	}

	public void setU_PHONE(String u_PHONE) {
		U_PHONE = u_PHONE;
	}

	public Date getREG_DATE() {
		return REG_DATE;
	}

	public void setREG_DATE(Date rEG_DATE) {
		REG_DATE = rEG_DATE;
	}

	public Date getMOD_DATE() {
		return MOD_DATE;
	}

	public void setMOD_DATE(Date mOD_DATE) {
		MOD_DATE = mOD_DATE;
	}

	public String getU_STATUS() {
		return U_STATUS;
	}

	public void setU_STATUS(String u_STATUS) {
		U_STATUS = u_STATUS;
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

	public String getR_NAME() {
		return R_NAME;
	}

	public void setR_NAME(String r_NAME) {
		R_NAME = r_NAME;
	}

	public String getR_EMAIL() {
		return R_EMAIL;
	}

	public void setR_EMAIL(String r_EMAIL) {
		R_EMAIL = r_EMAIL;
	}

	public String getR_type() {
		return R_type;
	}

	public void setR_type(String r_type) {
		R_type = r_type;
	}

	public String getR_STATUS() {
		return R_STATUS;
	}

	public void setR_STATUS(String r_STATUS) {
		R_STATUS = r_STATUS;
	}

	public Date getR_DATE() {
		return R_DATE;
	}

	public void setR_DATE(Date r_DATE) {
		R_DATE = r_DATE;
	}

	public int getR_NO() {
		return R_NO;
	}

	public void setR_NO(int r_NO) {
		R_NO = r_NO;
	}


	@Override
	public String toString() {
		return "Member [U_NO=" + U_NO + ", U_ID=" + U_ID + ", U_NICKNAME=" + U_NICKNAME + ", U_PW=" + U_PW
				+ ", U_PHONE=" + U_PHONE + ", REG_DATE=" + REG_DATE + ", MOD_DATE=" + MOD_DATE + ", U_STATUS="
				+ U_STATUS + ", R_DONG=" + R_DONG + ", R_HO=" + R_HO + ", R_NAME=" + R_NAME + ", R_EMAIL=" + R_EMAIL
				+ ", R_type=" + R_type + ", R_STATUS=" + R_STATUS + ", R_DATE=" + R_DATE + ", R_NO=" + R_NO + "]";
	}
	
	



	
	
	
}
