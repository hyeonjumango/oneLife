package admin.report.model.vo;

public class Search {
	private String status;
	private String reportNum;
	private String searchName;
	private String searchValue;
	
	public Search() {
		super();
	}
	public Search(String status, String reportNum, String searchName, String searchValue) {
		super();
		this.status = status;
		this.reportNum = reportNum;
		this.searchName = searchName;
		this.searchValue = searchValue;
	}
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReportNum() {
		return reportNum;
	}
	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	@Override
	public String toString() {
		return "Search [status=" + status + ", reportNum=" + reportNum + ", searchName=" + searchName + ", searchValue="
				+ searchValue + "]";
	}
	
}
