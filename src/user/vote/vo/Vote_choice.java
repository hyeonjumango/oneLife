package user.vote.vo;

public class Vote_choice {
	private int ve_no;
	private int v_no;
	private int vc_no;
	private String vc_val1;
	private String vc_val2;
	private String vc_val3;
	private String vc_val4;
	private String vc_val5;
	private int u_no;
	private int val1;
	private int val2;
	private int val3;
	private int val4;
	private int val5;
	
	
	/*VE_NO	NUMBER
		V_NO	NUMBER
		VC_NO	NUMBER
		VC_VAL1	NUMBER
		VC_VAL2	NUMBER
		VC_VAL3	NUMBER
		VC_VAL4	NUMBER
		VC_VAL5	NUMBER
		U_NO	NUMBER
	 * 
	 * */
	
	public Vote_choice() {}


	

	public Vote_choice(int ve_no, int v_no, String vc_val1, String vc_val2, String vc_val3, String vc_val4, String vc_val5, int u_no) {
		super();
		this.ve_no = ve_no;
		this.v_no = v_no;
		this.vc_val1 = vc_val1;
		this.vc_val2 = vc_val2;
		this.vc_val3 = vc_val3;
		this.vc_val4 = vc_val4;
		this.vc_val5 = vc_val5;
		this.u_no = u_no;
	}




	public Vote_choice(int ve_no, int v_no, String vc_val1, int u_no) {
		super();
		this.ve_no = ve_no;
		this.v_no = v_no;
		this.vc_val1 = vc_val1;
		this.u_no = u_no;
	}
	
	


	public Vote_choice(int val1, int val2, int val3, int val4, int val5) {
		super();
		this.val1 = val1;
		this.val2 = val2;
		this.val3 = val3;
		this.val4 = val4;
		this.val5 = val5;
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


	public int getVc_no() {
		return vc_no;
	}


	public void setVc_no(int vc_no) {
		this.vc_no = vc_no;
	}


	
	

	public String getVc_val1() {
		return vc_val1;
	}




	public void setVc_val1(String vc_val1) {
		this.vc_val1 = vc_val1;
	}




	public String getVc_val2() {
		return vc_val2;
	}




	public void setVc_val2(String vc_val2) {
		this.vc_val2 = vc_val2;
	}




	public String getVc_val3() {
		return vc_val3;
	}




	public void setVc_val3(String vc_val3) {
		this.vc_val3 = vc_val3;
	}




	public String getVc_val4() {
		return vc_val4;
	}




	public void setVc_val4(String vc_val4) {
		this.vc_val4 = vc_val4;
	}




	public String getVc_val5() {
		return vc_val5;
	}




	public void setVc_val5(String vc_val5) {
		this.vc_val5 = vc_val5;
	}




	public int getU_no() {
		return u_no;
	}




	public void setU_no(int u_no) {
		this.u_no = u_no;
	}

	



	public int getVal1() {
		return val1;
	}




	public void setVal1(int val1) {
		this.val1 = val1;
	}




	public int getVal2() {
		return val2;
	}




	public void setVal2(int val2) {
		this.val2 = val2;
	}




	public int getVal3() {
		return val3;
	}




	public void setVal3(int val3) {
		this.val3 = val3;
	}




	public int getVal4() {
		return val4;
	}




	public void setVal4(int val4) {
		this.val4 = val4;
	}




	public int getVal5() {
		return val5;
	}




	public void setVal5(int val5) {
		this.val5 = val5;
	}




	@Override
	public String toString() {
		return "Vote_choice [ve_no=" + ve_no + ", v_no=" + v_no + ", vc_no=" + vc_no + ", vc_val1=" + vc_val1
				+ ", vc_val2=" + vc_val2 + ", vc_val3=" + vc_val3 + ", vc_val4=" + vc_val4 + ", vc_val5=" + vc_val5
				+ ", u_no=" + u_no + ", val1=" + val1 + ", val2=" + val2 + ", val3=" + val3 + ", val4=" + val4
				+ ", val5=" + val5 + "]";
	}




	





	
	
		
	
	

}
