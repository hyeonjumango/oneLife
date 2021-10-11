package admin.vehicle.model.vo;

public class VisitCarSearch {
	private String dong;
	private String ho;
	private String carNo;
	private String date;
	private String status;
	
	public VisitCarSearch() {}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public VisitCarSearch(String dong, String ho, String carNo, String date, String status) {
		super();
		this.dong = dong;
		this.ho = ho;
		this.carNo = carNo;
		this.date = date;
		this.status = status;
	}

	@Override
	public String toString() {
		return "VisitCarSearch [dong=" + dong + ", ho=" + ho + ", carNo=" + carNo + ", date=" + date + ", status="
				+ status + "]";
	}
	
	
	
}
