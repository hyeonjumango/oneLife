package admin.facil.model.vo;

public class Search {
	private String facilName;
	private String facilType;
	private String facilStatus;
	private String facilDay;
	private String allDay;
	private String searchName;
	private String searchValue;

	public Search() {
		super();
	}

	public Search(String facilName, String facilType, String facilStatus, String facilDay, String allDay,
			String searchName, String searchValue) {
		super();
		this.facilName = facilName;
		this.facilType = facilType;
		this.facilStatus = facilStatus;
		this.facilDay = facilDay;
		this.allDay = allDay;
		this.searchName = searchName;
		this.searchValue = searchValue;
	}

	public String getFacilName() {
		return facilName;
	}

	public void setFacilName(String facilName) {
		this.facilName = facilName;
	}

	public String getFacilType() {
		return facilType;
	}

	public void setFacilType(String facilType) {
		this.facilType = facilType;
	}

	public String getFacilStatus() {
		return facilStatus;
	}

	public void setFacilStatus(String facilStatus) {
		this.facilStatus = facilStatus;
	}

	public String getFacilDay() {
		return facilDay;
	}

	public void setFacilDay(String facilDay) {
		this.facilDay = facilDay;
	}

	public String getAllDay() {
		return allDay;
	}

	public void setAllDay(String allDay) {
		this.allDay = allDay;
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
		return "Search [facilName=" + facilName + ", facilType=" + facilType + ", facilStatus=" + facilStatus
				+ ", facilDay=" + facilDay + ", allDay=" + allDay + ", searchName=" + searchName + ", searchValue="
				+ searchValue + "]";
	}

}
