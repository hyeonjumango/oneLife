package user.vote.vo;

public class Vote_example {
	private int ve_no;
	private int v_no; 
	private String ve_choice1;
	private String ve_choice2;
	private String ve_choice3;
	private String ve_choice4;
	private String ve_choice5;
	/*VE_NO	NUMBER
	V_NO	NUMBER
	VE_CHOICE1	VARCHAR2(20 BYTE)
	VE_CHOICE2	VARCHAR2(20 BYTE)
	VE_CHOICE3	VARCHAR2(20 BYTE)
	VE_CHOICE4	VARCHAR2(20 BYTE)
	VE_CHOICE5	VARCHAR2(20 BYTE)*/
	
	public Vote_example() {}

	public Vote_example(int ve_no, int v_no, String ve_choice1, String ve_choice2, String ve_choice3, String ve_choice4,
			String ve_choice5) {
		super();
		this.ve_no = ve_no;
		this.v_no = v_no;
		this.ve_choice1 = ve_choice1;
		this.ve_choice2 = ve_choice2;
		this.ve_choice3 = ve_choice3;
		this.ve_choice4 = ve_choice4;
		this.ve_choice5 = ve_choice5;
	}

	public int getVe_no() {
		return ve_no;
	}

	public void setVe_no(int ve_no) {
		this.ve_no = ve_no;
	}

	public int getV_no() {
		return v_no;
	}

	public void setV_no(int v_no) {
		this.v_no = v_no;
	}

	public String getVe_choice1() {
		return ve_choice1;
	}

	public void setVe_choice1(String ve_choice1) {
		this.ve_choice1 = ve_choice1;
	}

	public String getVe_choice2() {
		return ve_choice2;
	}

	public void setVe_choice2(String ve_choice2) {
		this.ve_choice2 = ve_choice2;
	}

	public String getVe_choice3() {
		return ve_choice3;
	}

	public void setVe_choice3(String ve_choice3) {
		this.ve_choice3 = ve_choice3;
	}

	public String getVe_choice4() {
		return ve_choice4;
	}

	public void setVe_choice4(String ve_choice4) {
		this.ve_choice4 = ve_choice4;
	}

	public String getVe_choice5() {
		return ve_choice5;
	}

	public void setVe_choice5(String ve_choice5) {
		this.ve_choice5 = ve_choice5;
	}

	@Override
	public String toString() {
		return "Vote_example [ve_no=" + ve_no + ", v_no=" + v_no + ", ve_choice1=" + ve_choice1 + ", ve_choice2="
				+ ve_choice2 + ", ve_choice3=" + ve_choice3 + ", ve_choice4=" + ve_choice4 + ", ve_choice5="
				+ ve_choice5 + "]";
	}
	 
	
	
	

}
