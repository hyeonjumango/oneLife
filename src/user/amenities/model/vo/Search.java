package user.amenities.model.vo;

public class Search {
	private String mydate;
	private int u_no;
	
	public Search() {}

	public Search(String mydate, int u_no) {
		super();
		this.mydate = mydate;
		this.u_no = u_no;
	}

	public String getMydate() {
		return mydate;
	}

	public void setMydate(String mydate) {
		this.mydate = mydate;
	}

	public int getU_no() {
		return u_no;
	}

	public void setU_no(int u_no) {
		this.u_no = u_no;
	}

	@Override
	public String toString() {
		return "Search [mydate=" + mydate + ", u_no=" + u_no + "]";
	}

	

	
	
		

	

}
