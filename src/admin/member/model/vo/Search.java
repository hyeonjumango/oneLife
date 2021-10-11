package admin.member.model.vo;

public class Search {
	private String rDong;
	private String rHo;
	private String rType;
	private String rStatus;
	private String searchName;
	private String searchValue;

	public Search() { }

	public Search(String rDong, String rHo, String rType, String rStatus, String searchName, String searchValue) {
		super();
		this.rDong = rDong;
		this.rHo = rHo;
		this.rType = rType;
		this.rStatus = rStatus;
		this.searchName = searchName;
		this.searchValue = searchValue;
	}

	public String getrDong() {
		return rDong;
	}

	public void setrDong(String rDong) {
		this.rDong = rDong;
	}

	public String getrHo() {
		return rHo;
	}

	public void setrHo(String rHo) {
		this.rHo = rHo;
	}

	public String getrType() {
		return rType;
	}

	public void setrType(String rType) {
		this.rType = rType;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
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
		return "Search [rDong=" + rDong + ", rHo=" + rHo + ", rType=" + rType + ", rStatus=" + rStatus + ", searchName="
				+ searchName + ", searchValue=" + searchValue + "]";
	}
	
	

}
