package user.vote.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.board.model.vo.Search;
import user.vote.model.voteService;
import user.vote.vo.Vote;

/**
 * Servlet implementation class VoteListServlet
 */
@WebServlet("/vote/list")
public class VoteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// * page : 현재 요청하는 페이지 값 (기본적으로 게시판은 1페이지부터 시작)
				int page = 1;
				
				// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				
				// 검색 조건과 검색 값 추가하기
				String searchCondition = request.getParameter("searchCondition");
				String searchValue = request.getParameter("searchValue");
				
				// 요청 페이지 값을 매개변수로 넘기고 조회 된 게시글 리스트 + 페이징 처리에 대한 객체 값 map 타입에 담아 리턴
				Map<String, Object> map = new voteService().selectlist(page, new Search(searchCondition, searchValue));
				
				System.out.println("voteList : " + map.get("voteList"));
				
				request.setAttribute("pi", map.get("pi"));
				request.setAttribute("voteList", map.get("voteList"));
				request.getRequestDispatcher("/WEB-INF/views/user/jsp/vote/voteListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
