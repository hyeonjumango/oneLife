package user.VisitCar.model.vo;

public class UserVisitCarSearch {
	private String date;
	private String carNo;
	private String applicant;
	
	public UserVisitCarSearch() {}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public UserVisitCarSearch(String date, String carNo, String applicant) {
		super();
		this.date = date;
		this.carNo = carNo;
		this.applicant = applicant;
	}

	@Override
	public String toString() {
		return "UserVisitCarSearch [date=" + date + ", carNo=" + carNo + ", applicant=" + applicant + "]";
	}
	
	
}
