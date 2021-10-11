package user.notice.model.vo;

public class PageInfo {
	private int page;             // 요청페이지
	private int listCount;        // 게시글 수
	private int pageLimit;        // 한 페이지 하단에 보여질 페이징바의 개수
	private int boardLimit;       // 한 페이지 에 보여질 게시글 최대 수
	private int maxPage;          // 전체 페이지에서 가장 마지막 페이지
	private int startPage;        // 한 페이지 하단에 보여질 페이징바의 시작 값
	private int endPage;          // 한 페이지 하단에 보여질 페이징바의 마지막 값
	
	public PageInfo() {}

	// 페이징 처리 계산에 필요한 값을 받아 max/start/endPage 계산하여 설정하기
	public PageInfo(int page, int listCount, int pageLimit, int boardLimit) {
		super();
		this.page = page;
		this.listCount = listCount;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		
		// * maxPage : 총 페이지 중 마지막 페이지 수
		// 글 개수가 105개이면 총 페이지 수는 자투리 5개까지 한페이지로 생각해서 11페이지가 필요함
		// 전체 게시글 수 (105) /  한페이지에 보여질 게시글 수 (10) 의 결과를 올림 처리
		this.maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		// * startPage : 현재 페이지에 보여지는 페이징 바의 시작 수 
		// 현재 요청 페이지(page)에서 pageLimit만큼을 나누고 다시 곱한 뒤 1을 더함
		// 5(page 요청) / 10 * 10 + 1 -> 1
		// 15(page 요청) / 10 * 10 + 1 -> 11
		// 25(page 요청) / 10 * 10 + 1 -> 21
		// * 10, 20, 30의 경우 나누어 떨어지는 숫자여서 몫이 하나 더 늘어나는 문제가 있으므로
		//   해당 예외를 방지하기 위헤 page - 1을 함
		this.startPage = (page-1) / pageLimit * pageLimit + 1;
		
		// * endPage : 현재 페이지에 보여지는 페이징 바의 마지막 수 
		this.endPage = startPage + pageLimit -1;
		
		// endPage가 maxPage보다 클 수 없으므로
		// (maxPage가 11인데 endPage가 20일 수는 없음)
		if(maxPage < endPage)
			endPage = maxPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", listCount=" + listCount + ", pageLimit=" + pageLimit + ", boardLimit="
				+ boardLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
	
	
	
}
