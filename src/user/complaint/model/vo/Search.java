package user.complaint.model.vo;

public class Search {
	private String searchCondition;
	private String searchValue;
	private String u_id;
	
	public Search() {}
		

	

	public Search(String searchCondition, String searchValue, String u_id) {
		super();
		this.searchCondition = searchCondition;
		this.searchValue = searchValue;
		this.u_id = u_id;
	}




	public String getSearchCondition() {
		return searchCondition;
	}


	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}


	public String getSearchValue() {
		return searchValue;
	}


	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	


	public String getU_id() {
		return u_id;
	}




	public void setU_id(String u_id) {
		this.u_id = u_id;
	}




	@Override
	public String toString() {
		return "Search [searchCondition=" + searchCondition + ", searchValue=" + searchValue + ", u_id=" + u_id + "]";
	}




	
	

}
