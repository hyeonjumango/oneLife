package admin.manager.model.vo;

public class Search {
	private String managerListSearch; // 검색 조건
	private String managerListValue;  // 검색 명
	
	public Search() {}

	public Search(String managerListSearch, String managerListValue) {
		super();
		this.managerListSearch = managerListSearch;
		this.managerListValue = managerListValue;
	}

	public String getManagerListSearch() {
		return managerListSearch;
	}

	public void setManagerListSearch(String managerListSearch) {
		this.managerListSearch = managerListSearch;
	}

	public String getManagerListValue() {
		return managerListValue;
	}

	public void setManagerListValue(String managerListValue) {
		this.managerListValue = managerListValue;
	}

	@Override
	public String toString() {
		return "Search [managerListSearch=" + managerListSearch + ", managerListValue=" + managerListValue + "]";
	}
	
}
