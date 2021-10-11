package admin.manager.model.vo;

public class Manager {
	/*
	 * M_NO	NUMBER	No		1	관리자 번호
		M_ID	VARCHAR2(20 BYTE)	No		2	관리자 아이디
		M_PASSWORD	VARCHAR2(20 BYTE)	Yes		3	관리자 비밀번호
		M_NAME	VARCHAR2(10 BYTE)	Yes		4	관리자 이름
		M_NICK	VARCHAR2(12 BYTE)	Yes	"'관리자'	"	5	관리자 닉네임
		M_PHONE	VARCHAR2(13 BYTE)	Yes		6	관리자 전화번호
		M_JOBCODE	VARCHAR2(10 BYTE)	No		7	관리자 직급코드
	 * */
	private int mNo;
	private String mId;
	private String mPassword;
	private String mName;
	private String mNick;
	private String mPhone;
	private String mJobcode;
	private String mJobName;
	
	
	public Manager() {
		super();
	}
	
	
	public Manager(int mNo, String mId, String mName, String mPhone, String mJobcode) {
		super();
		this.mNo = mNo;
		this.mId = mId;
		this.mName = mName;
		this.mPhone = mPhone;
		this.mJobcode = mJobcode;
	}





	public Manager(String mId, String mPassword, String mName, String mPhone) {
		super();
		this.mId = mId;
		this.mPassword = mPassword;
		this.mName = mName;
		this.mPhone = mPhone;
	}



	public int getmNo() {
		return mNo;
	}
	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmJobName() {
		return mJobName;
	}


	public void setmJobName(String mJobName) {
		this.mJobName = mJobName;
	}


	public String getmPassword() {
		return mPassword;
	}
	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmNick() {
		return mNick;
	}
	public void setmNick(String mNick) {
		this.mNick = mNick;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getmJobcode() {
		return mJobcode;
	}
	public void setmJobcode(String mJobcode) {
		this.mJobcode = mJobcode;
	}


	@Override
	public String toString() {
		return "Manager [mNo=" + mNo + ", mId=" + mId + ", mPassword=" + mPassword + ", mName=" + mName + ", mNick="
				+ mNick + ", mPhone=" + mPhone + ", mJobcode=" + mJobcode + ", mJobName=" + mJobName + "]";
	}


	
	
	
}
