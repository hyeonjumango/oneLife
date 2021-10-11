package user.complaint.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import user.complaint.model.service.complaintService;
import user.complaint.model.vo.Search;
import user.complaint.model.vo.complaint;
import user.complaint.model.vo.complaint_manager;

/**
 * Servlet implementation class ComplaintListServlet
 */
@WebServlet("/complaint/list")
public class ComplaintListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplaintListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

        List<complaint_manager> complaint_man_List = new complaintService().selectList();
	
        
		
		// * page : 현재 요청하는 페이지 값 (기본적으로 게시판은 1페이지부터 시작)
		int page = 1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 검색 조건과 검색 값 추가하기
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		String u_id = request.getParameter("u_id");
		
		// 요청 페이지 값을 매개변수로 넘기고 조회 된 게시글 히스트 + 페이징 처리에 대한 객체 값 map 타입에 담아 리턴
		Map<String, Object> map = new complaintService().selectlist(page, new Search(searchCondition, searchValue, u_id));
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("complaintList", map.get("complaintList"));
		request.setAttribute("complaint_man_List", complaint_man_List);
		
	
		request.getRequestDispatcher("/WEB-INF/views/user/jsp/complaint/complaintListView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
