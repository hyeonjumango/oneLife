package admin.week.model.vo;

public class Search {
	private String sType;
	private String sReveal;
	private String searchDay;
	private String allDay;
	private String searchName;
	private String searchValue;
	
	public Search(String sType, String sReveal, String searchDay, String allDay, String searchName,
			String searchValue) {
		super();
		this.sType = sType;
		this.sReveal = sReveal;
		this.searchDay = searchDay;
		this.allDay = allDay;
		this.searchName = searchName;
		this.searchValue = searchValue;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public String getsReveal() {
		return sReveal;
	}

	public void setsReveal(String sReveal) {
		this.sReveal = sReveal;
	}

	public String getSearchDay() {
		return searchDay;
	}

	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
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
		return "Search [sType=" + sType + ", sReveal=" + sReveal + ", searchDay=" + searchDay + ", allDay=" + allDay
				+ ", searchName=" + searchName + ", searchValue=" + searchValue + "]";
	}
	
}
