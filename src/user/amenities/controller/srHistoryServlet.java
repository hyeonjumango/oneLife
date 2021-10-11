package user.amenities.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.amenities.model.service.AmentiesService;
import user.amenities.model.vo.Search;
import user.member.model.vo.Member;


/**
 * Servlet implementation class srHistoryServlet
 */
@WebServlet("/srHistory")
public class srHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Member m = (Member) request.getSession().getAttribute("loginUser");
			if (m == null) {
				request.getSession().setAttribute("msg", "입주민 전용 공간입니다.");
				response.sendRedirect(request.getContextPath()+"/main");
			} else {
				
				int page = 1;
				
				// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				
				// 검색 조건과 검색 값 추가하기
				String mydate = request.getParameter("mydate");
				int u_no = ((Member)request.getSession().getAttribute("loginUser")).getU_NO();
				
				
				
				// 요청 페이지 값을 매개변수로 넘기고 조회 된 게시글 히스트 + 페이징 처리에 대한 객체 값 map 타입에 담아 리턴
				Map<String, Object> map = new AmentiesService().selectlist(page, new Search(mydate, u_no));
				
				request.setAttribute("pi", map.get("pi"));
				request.setAttribute("studyRoomList", map.get("studyRoomList"));
				
			
				request.getRequestDispatcher("WEB-INF/views/user/jsp/amenities/studyRoomHistory.jsp").forward(request, response);
				
			}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
