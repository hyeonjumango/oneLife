package user.board.model.vo;

public class Board_Report {
	private int u_no;
	private int b_no;
	private int bc_no;
	
	
	/*U_NO	NUMBER
		B_NO	NUMBER
		BC_NO	NUMBER*/
	
	
	public Board_Report() {}


	public Board_Report(int u_no, int b_no, int bc_no) {
		super();
		this.u_no = u_no;
		this.b_no = b_no;
		this.bc_no = bc_no;
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


	public int getBc_no() {
		return bc_no;
	}


	public void setBc_no(int bc_no) {
		this.bc_no = bc_no;
	}


	@Override
	public String toString() {
		return "Board_Report [u_no=" + u_no + ", b_no=" + b_no + ", bc_no=" + bc_no + "]";
	}
	
	
	
	
	

}
